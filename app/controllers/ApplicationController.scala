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



  def viewOrderErrors = Action { implicit request =>
    Ok(views.html.order_errors())
  }

  def newOrderError = Action { implicit request =>
    Ok(views.html.new_error())
  }


}
