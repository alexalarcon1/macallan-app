package controllers

import com.google.inject.Inject
import dao.{NewOrdersDao, OrdersDao, ProductsDao}
import models.{NewOrder, Order}
import play.api.Logger
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
class OrdersController @Inject()(newOrdersDao: NewOrdersDao, productsDao: ProductsDao) extends Controller {

 /* def getOrders = Action.async { implicit request =>
    ordersDao.findByStatus("pendng").map { order =>
      Ok(Json.toJson(order))
    }
  }*/

/*  def newOrder = Action { implicit request =>
    Ok(views.html.new_order())
  }*/

  def viewCurrentOrders = Action { implicit request =>
    Ok(views.html.current_orders())
  }

  def viewOrderHistory = Action { implicit request =>
    Ok(views.html.order_history())
  }

  //TODO bind OrderForm to create new orders
  val newOrderForm: Form[NewOrder] = Form {
    mapping(
      "id" -> ignored(0L),
      "product_name" -> nonEmptyText(2,15),
      "product_brand" -> nonEmptyText(2,15),
      "product_size" -> nonEmptyText(4),
      "product_quantity" -> longNumber
    )(NewOrder.apply)(NewOrder.unapply)
  }

  def getAllOrders = Action.async { implicit request =>
    newOrdersDao.findAll().map { order =>
      Ok(Json.toJson(order))
    }
  }

  def addNewOrder(/*oldQuantity: Seq[Long]*/) = Action.async { implicit  request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle success.
    val ord = newOrderForm.bindFromRequest().get

    /*val qt = oldQuantity.map(q => q)
    val newQt = qt - ord.product_quantity

    productsDao.updateQuantity(ord.id, newQt)
*/
    newOrdersDao.insert(ord.product_name, ord.product_brand, ord.product_size, ord.product_quantity)
    Future(Redirect(routes.OrdersController.viewCurrentOrders()))
  }
}
