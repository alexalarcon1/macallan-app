package dao

import com.google.inject.Inject
import models.{Person, User}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver
import slick.driver.JdbcProfile

import scala.concurrent.Future
/**
  * Created by alex_alarcon on 4/19/2016.
  */
class UsersDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class Users(tag: Tag) extends Table[User](tag, "users") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def username = column[String]("username")
    def password = column[String]("password")
    def firstName = column[String]("first_name")
    def lastName = column[String]("last_name")
    def address = column[String]("address")
    def phoneNumber = column[String]("phone_number")
    def role = column[String]("role")

    def * = (id, username, password, firstName, lastName, address, phoneNumber, role) <> ((User.apply _).tupled, User.unapply _)
  }

  val users = TableQuery[Users]

  def findAll() = {
    val q = for {
      u <- users
    } yield u
    db.run(q.sortBy(u => u.id.asc).result)
  }

  def insert(username: String, password: String, firstName: String, lastName: String, address: String, phoneNumber: String, role: String): Future[User] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (users.map(u => (u.username, u.password, u.firstName, u.lastName, u.address, u.phoneNumber, u.role))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning users.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((userInfo, id) => User(id, userInfo._1, userInfo._2, userInfo._3, userInfo._4, userInfo._5, userInfo._6, userInfo._7))
      // And finally, insert the person into the database
      ) += (username, password, firstName, lastName, address, phoneNumber, role)
  }
}

