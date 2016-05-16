package models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Order(id: Long=0,
                 userId: Long,
                 order_date: java.sql.Date,
                 ordered_at: Option[Timestamp] = None,
                 status: String
                ) {

  def toJson = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd")

    var jsObj = Json.obj("id" -> JsNumber(id),
                         "userId" -> JsNumber(userId),
                         "order_date" -> JsString(formatter.format(order_date)),
                         "status" -> JsString(status)
    )
    ordered_at.foreach(ord_at => jsObj = jsObj ++ Json.obj("ordered_at" -> JsNumber(ord_at.getTime / 1000L)))

    jsObj
  }

  def toJsonWithProductCount(count: Int): JsObject = {
    toJson + ("number_of_products" -> JsNumber(count))

  }

  def toJsonWithProducts(productSequence: Seq[Product]): JsObject = {
    toJson + ("products" -> JsArray(productSequence.map { p =>
      Product.productWrites.writes(p)
    }))
  }
}

/*object Order {
  /*implicit val orderFormat = Json.format[Order]*/
  implicit val orderWrites = new Writes[Order] {
    def writes(o: Order) = Json.obj(
    "id" -> JsNumber(o.id),
    "userId" -> JsNumber(o.userId),
    "order_date" -> JsString(o.order_date),
    "ordered_at" -> JsString(o.ordered_at),
    "status" -> JsString(o.status)
  }

}*/

case class  NewOrder(product_name: String,
                     product_brand: String,
                     product_size: Double,
                     product_quantity: Long)
                    //products: List[NewProducts])
object NewOrder {
  implicit val reads: Reads[NewOrder] = (
      (JsPath \ "order" \ "product_name").read[String] and
      (JsPath \ "order" \ "product_brand").read[String] and
      (JsPath \ "order" \ "product_size").read[Double] and
      (JsPath \ "order" \ "product_quantity").read[Long]
      //(JsPath \ "order" \ "products").read[List[NewProducts]]
    )(NewOrder.apply _)

}

case class NewProducts(name: String,
                       brand: String,
                       price: Double,
                       size: String,
                       kind: String,
                       quantity: Long,
                       percentage: Double,
                       origin: String,
                       status: String)
object NewProducts {
  implicit val reads: Reads[NewProducts] = (
    (JsPath \ "name").read[String] and
      (JsPath \"brand").read[String] and
      (JsPath \"price").read[Double] and
      (JsPath \"size").read[String] and
      (JsPath \"kind").read[String] and
      (JsPath \ "quantity").read[Long] and
      (JsPath \"percentage").read[Double] and
      (JsPath \"origin").read[String] and
      (JsPath \"status").read[String]
    )(NewProducts.apply _)
  }
