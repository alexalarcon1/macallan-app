package controllers

import com.google.inject.Inject
import dao.OrdersDao
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

  //TODO OrderForm to create new orders
}
