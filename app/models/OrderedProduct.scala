package models

import play.api.libs.json.{JsNumber, Json, Writes}

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class OrderedProduct(id: Long=0,
                          productId: Long,
                          orderId: Long) {

  def toJson = {
    Json.obj("id" -> JsNumber(id),
             "productId" -> JsNumber(productId),
             "orderId" -> JsNumber(orderId))
  }

  object OrderedProduct {
    implicit val orderedProductWrites = new Writes[OrderedProduct] {
      def writes(p: OrderedProduct) = Json.obj(
        "id" -> JsNumber(p.id),
        "productId" -> JsNumber(p.productId),
        "orderId" -> JsNumber(p.orderId))
    }
  }
}
