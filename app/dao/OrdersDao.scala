package dao

import java.sql.Timestamp

import com.google.inject.Inject
import models.{NewOrder, Order}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future


/**
  * Created by alex_alarcon on 5/8/2016.
  */
class OrdersDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class Orders(tag: Tag) extends Table[Order](tag, "orders") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def userId = column[Long]("userId")
    def order_date = column[java.sql.Date]("order_date")
    def ordered_at = column[Timestamp]("ordered_at")
    def status = column[String]("status")

    def * = (id, userId, order_date, ordered_at.?, status) <> ((Order.apply _).tupled, Order.unapply _)
  }

  val orders = TableQuery[Orders]


  def findOrderById(orderId: Long) = {
    val a = for {
      o <- orders if o.id === orderId
    } yield o
    db.run(a.result)
  }

  def findByStatus(status: String) = {
    val o = orders.filter(_.status === status)
    db.run(o.result.head)
  }

 /* def insert(name: String, brand: String, size: Double, quantity: Long): Future[NewOrder] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (products.map(p => (p.name, p.brand, p.price, p.size, p.kind, p.quantity, p.percentage, p.origin, p.status))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning products.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((pInfo, id) => Product(id, pInfo._1, pInfo._2, pInfo._3, pInfo._4, pInfo._5, pInfo._6, pInfo._7, pInfo._8, pInfo._9))
      // And finally, insert the person into the database
      ) += (name, brand, price, size, kind, quantity, percentage, origin, status)
  }*/


}
