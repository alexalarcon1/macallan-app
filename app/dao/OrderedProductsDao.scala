package dao

import com.google.inject.Inject
import models.OrderedProduct
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class OrderedProductsDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {
  import driver.api._

  class OrderedProducts(tag: Tag) extends Table[OrderedProduct](tag, "orderedProduct") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def userId = column[Long]("userId")
    def orderId = column[Long]("orderId")

    def * = (id, userId, orderId) <> ((OrderedProduct.apply _).tupled, OrderedProduct.unapply _)
  }

  val orderedProducts = TableQuery[OrderedProducts]

  def findPairById(id: Long) = {
    val q = for {
      osp <- orderedProducts if osp.id === id
    } yield osp
    db.run(q.result.head)
  }

}
