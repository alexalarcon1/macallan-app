package models

import play.api.libs.json.Json

/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Supplier(id: Long=0,
                    name: String,
                    address: String) {
}

object Supplier {
  implicit val supplierFormat = Json.format[Supplier]

}
