package dao

import com.google.inject.Inject
import models.{Product, User}
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

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
    def size = column[Double]("size")
    def kind = column[String]("kind")
    def quantity = column[Long]("quantity")
    def percentage = column[Double]("percentage")
    def origin = column[String]("origin")
    def status = column[String]("status")

    def * = (id, name, brand, price, size, kind, quantity, percentage, origin, status) <> ((Product.apply _).tupled, Product.unapply _)
  }

  val products = TableQuery[Products]

  def findAll() = {
    val q = for {
      p <- products
    } yield p
    db.run(q.sortBy(p => p.id.asc).result)
  }

  def insert(name: String, brand: String, price: Double, size: Double, kind: String, quantity: Long, percentage: Double, origin: String, status: String ): Future[Product] = db.run {
    // We create a projection of just the name and age columns, since we're not inserting a value for the id column
    (products.map(p => (p.name, p.brand, p.price, p.size, p.kind, p.quantity, p.percentage, p.origin, p.status))
      // Now define it to return the id, because we want to know what id was generated for the person
      returning products.map(_.id)
      // And we define a transformation for the returned value, which combines our original parameters with the
      // returned id
      into ((pInfo, id) => Product(id, pInfo._1, pInfo._2, pInfo._3, pInfo._4, pInfo._5, pInfo._6, pInfo._7, pInfo._8, pInfo._9))
      // And finally, insert the person into the database
      ) += (name, brand, price, size, kind, quantity, percentage, origin, status)
  }

}
