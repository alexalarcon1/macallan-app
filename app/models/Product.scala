package models

import play.api.libs.json.{JsNumber, JsString, Json}

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Product(id: Long=0,
                   name: String,
                   brand: String,
                   price: Double,
                   size: Double,
                   kind: String,
                   percentage: Double,
                   country: String,
                   status: String,
                   supplier_id: Long
                  ) {
  def toJson = {
    Json.obj("id" -> JsNumber(id),
      "name" -> JsString(name),
      "brand" -> JsString(brand),
      "price" -> JsNumber(price),
      "size" -> JsNumber(size),
      "kind" -> JsString(kind),
      "percentage" -> JsNumber(percentage),
      "country" -> JsString(country),
      "status" -> JsString(status),
      "supplier_id" -> JsNumber(supplier_id)
    )
  }
}

object Product {
  implicit val productFormat = Json.format[Product]

}
