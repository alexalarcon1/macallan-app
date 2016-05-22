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
class NewOrdersDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class NewOrders(tag: Tag) extends Table[NewOrder](tag, "new_orders") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def product_name = column[String]("product_name")
    def product_brand = column[String]("product_brand")
    def product_size = column[String]("product_size")
    def product_quantity = column[Long]("product_quantity")

    def * = (id, product_name, product_brand, product_size, product_quantity) <> ((NewOrder.apply _).tupled, NewOrder.unapply _)
  }

  val newOrders = TableQuery[NewOrders]

  def findAll() = {
    val q = for {
      orders <- newOrders
    } yield orders
    db.run(q.sortBy(no => no.id.asc).result)
  }

  def findOrderById(orderId: Long) = {
    val a = for {
      o <- newOrders if o.id === orderId
    } yield o
    db.run(a.result)
  }

  /*def findByStatus(status: String) = {
    val o = newOrders.filter(_.status === status)
    db.run(o.result.head)
  }*/

   def insert(name: String, brand: String, size: String, quantity: Long): Future[NewOrder] = db.run {
     (newOrders.map(no => (no.product_name, no.product_brand, no.product_size, no.product_quantity))
       returning newOrders.map(_.id)
       into ((nOrd, id) => NewOrder(id, nOrd._1, nOrd._2, nOrd._3, nOrd._4))
       ) += (name, brand, size, quantity)
   }


}
