package models

import java.sql.Timestamp
import java.text.SimpleDateFormat

import play.api.libs.json.Json
/**
  * Created by alex_alarcon on 5/8/2016.
  */
case class Order(id: Long=0,
                 products: List[Product],
                 order_date: Option[Timestamp],
                 status: String
                ) {
}

object Order {
  implicit val orderFormat = Json.format[Order]

}
