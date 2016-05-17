package controllers

import com.google.inject.Inject
import dao.OrdersDao
import models.{NewOrder, Order}
import play.api.data.Form
import play.api.data.Forms._
import play.api.data.format.Formats._
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class OrdersController @Inject()(ordersDao: OrdersDao) extends Controller {

 /* def getOrders = Action.async { implicit request =>
    ordersDao.findByStatus("pendng").map { order =>
      Ok(Json.toJson(order))
    }
  }*/

  //TODO bind OrderForm to create new orders
  val newOrderForm: Form[NewOrder] = Form {
    mapping(
      "product_name" -> text,
      "product_brand" -> text,
      "product_size" -> of(doubleFormat),
      "product_quantity" -> longNumber
    )(NewOrder.apply)(NewOrder.unapply)
  }
}
