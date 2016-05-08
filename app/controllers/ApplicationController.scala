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

  def dashboard = Action { implicit request =>
    Ok(views.html.dashboard())
  }

  def login = Action { implicit request =>
    Ok(views.html.login())
  }
}
