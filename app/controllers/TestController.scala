package controllers

import play.api.mvc.{Action, Controller}

/**
  * Created by alex_alarcon on 4/21/2016.
  */
class TestController extends Controller {

  def index = Action {
    Ok(views.html.reactTest())
  }
}
