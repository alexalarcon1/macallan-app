package models

import play.api.libs.json.{JsNumber, JsString, Json, Writes}

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Product(id: Long=0,
                   name: String,
                   brand: String,
                   price: Double,
                   size: Double,
                   kind: String,
                   quantity: Long,
                   percentage: Double,
                   origin: String,
                   status: String
                  ) {
  def toJson = {
    Json.obj("id" -> JsNumber(id),
      "name" -> JsString(name),
      "brand" -> JsString(brand),
      "price" -> JsNumber(price),
      "size" -> JsNumber(size),
      "quantity" -> JsNumber(quantity),
      "kind" -> JsString(kind),
      "percentage" -> JsNumber(percentage),
      "origin" -> JsString(origin),
      "status" -> JsString(status)
    )
  }
}

object Product {
  implicit val productWrites = new Writes[Product] {
    def writes(p: Product) = Json.obj(
      "id" -> JsNumber(p.id),
      "name" -> JsString(p.name),
      "brand" -> JsString(p.brand),
      "price" -> JsNumber(p.price),
      "size" -> JsNumber(p.size),
      "quantity" -> JsNumber(p.quantity),
      "kind" -> JsString(p.kind),
      "percentage" -> JsNumber(p.percentage),
      "origin" -> JsString(p.origin),
      "status" -> JsString(p.status)
    )
  }
}

