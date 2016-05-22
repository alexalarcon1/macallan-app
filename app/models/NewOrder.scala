package models

import play.api.libs.json.{JsNumber, JsString, Json, Writes}

/**
  * Created by alex_alarcon on 5/21/2016.
  */

case class  NewOrder(id: Long=0,
                     product_name: String,
                     product_brand: String,
                     product_size: String,
                     product_quantity: Long) {
  def toJson = {
    Json.obj("id" -> JsNumber(id),
      "product_name" -> JsString(product_name),
      "product_brand" -> JsString(product_brand),
      "product_size" -> JsString(product_size),
      "product_quantity" -> JsNumber(product_quantity)
    )
  }
}

object NewOrder {
  implicit val newOrderWrites = new Writes[NewOrder] {
    def writes(nOrd: NewOrder) = Json.obj(
      "id" -> JsNumber(nOrd.id),
      "name" -> JsString(nOrd.product_name),
      "brand" -> JsString(nOrd.product_brand),
      "size" -> JsString(nOrd.product_size),
      "quantity" -> JsNumber(nOrd.product_quantity)
    )
  }

}
//products: List[NewProducts])
/*object NewOrder {
  implicit val reads: Reads[NewOrder] = (
      (JsPath \ "order" \ "product_name").read[String] and
      (JsPath \ "order" \ "product_brand").read[String] and
      (JsPath \ "order" \ "product_size").read[Double] and
      (JsPath \ "order" \ "product_quantity").read[Long]
      //(JsPath \ "order" \ "products").read[List[NewProducts]]
    )(NewOrder.apply _)

}*/