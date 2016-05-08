package models

import play.api.libs.json._

/**
  * Created by alex_alarcon on 4/18/2016.
  */
case class User(id:Long=0,
                username: String,
                password: String,
                firstName: String,
                lastName: String,
                address: String,
                phoneNumber: String,
                role: String) {

  def toJson = {
    Json.obj("id" -> JsNumber(id),
      "username" -> JsString(username),
      "password" -> JsString(password),
      "first_name" -> JsString(firstName),
      "last_name" -> JsString(lastName),
      "address" -> JsString(address),
      "phoneNumber" -> JsString(phoneNumber),
      "role" -> JsString(role)
    )
  }
}

object User {
  implicit val userFormat = Json.format[User]
}
