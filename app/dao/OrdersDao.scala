package dao

import java.sql.Timestamp

import com.google.inject.Inject
import models.Order
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


}
