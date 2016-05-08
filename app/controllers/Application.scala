package controllers

import play.api.mvc._

/**
  * Created by alex_alarcon on 4/19/2016.
  */
/*
trait ApplicationComponent {
  this: Controller =>
    def index = Action { implicit r =>
      Ok(views.html.index())
    }
}*/

class Application extends Controller {

  def index = Action { implicit request =>
    Ok(views.html.index())
  }

  def login = Action { implicit request =>
    Ok(views.html.login())
  }
}
