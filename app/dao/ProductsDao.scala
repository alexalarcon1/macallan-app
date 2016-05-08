package dao

import com.google.inject.Inject
import models.Product
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class ProductsDao @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile]{
  import driver.api._

  class Products(tag: Tag) extends Table[Product](tag, "products") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def brand = column[String]("brand")
    def price = column[Double]("price")
    def size = column[String]("size")
    def kind = column[String]("kind")
    def percentage = column[Double]("percentage")
    def origin = column[String]("origin")
    def status = column[String]("status")

    def * = (id, name, brand, price, size, kind, percentage, origin, status) <> ((Product.apply _).tupled, Product.unapply _)
  }

  val products = TableQuery[Products]

  def findAll() = {
    val q = for {
      p <- products
    } yield p
    db.run(q.sortBy(p => p.id.asc).result)
  }

}
