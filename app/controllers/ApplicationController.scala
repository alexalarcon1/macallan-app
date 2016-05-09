package controllers

import play.api.mvc._

/**
  * Created by alex_alarcon on 4/19/2016.
  */
/*
trait ApplicationComponent {
  this: Controller =>
    def dashboard = Action { implicit r =>
      Ok(views.html.dashboard())
    }
}*/

class ApplicationController extends Controller {

  def login = Action { implicit request =>
    Ok(views.html.login())
  }

  def dashboard = Action { implicit request =>
    Ok(views.html.dashboard())
  }

  def inventory = Action { implicit request  =>
    Ok(views.html.inventory())
  }

  def newOrder = Action { implicit request =>
    Ok(views.html.new_order())
  }

  def viewCurrentOrders = Action { implicit request =>
    Ok(views.html.current_orders())
  }

  def viewOrderHistory = Action { implicit request =>
    Ok(views.html.order_history())
  }

  def viewOrderErrors = Action { implicit request =>
    Ok(views.html.order_errors())
  }

  def newOrderError = Action { implicit request =>
    Ok(views.html.new_error())
  }

  def newProduct = Action { implicit request =>
    Ok(views.html.new_product())
  }
}
