package models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Order(id: Long=0,
                 products: List[Product],
                 order_date: java.sql.Date,
                 ordered_at: Option[Timestamp],
                 status: String
                ) {

  def toJson = {
    val formatter = new SimpleDateFormat("yyyy-MM-dd")

    var jsObj = Json.obj("id" -> JsNumber(id),
      "order_date" -> JsString(formatter.format(order_date)),
      "status" -> JsString(status)
    )
    ordered_at.foreach(ord_at => jsObj = jsObj ++ Json.obj("ordered_at" -> JsNumber(ord_at.getTime / 1000L)))

    jsObj
  }

  def toJsonWithProducts(productSequence: Seq[Product]): JsObject = {

    toJson + ("order_shoe_pairs" -> JsArray(productSequence.map { p =>
      Product.productWrites.writes(p)
    }))
  }
}

object Order {
  implicit val orderFormat = Json.format[Order]

}
