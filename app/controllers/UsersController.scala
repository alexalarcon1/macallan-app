package controllers

import com.google.inject.Inject
import dao.UsersDao
import models.User
import play.api.data._
import play.api.data.Forms._
import play.api.libs.json.Json
import play.api.mvc.Controller
import play.api.mvc._


import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by alex_alarcon on 4/19/2016.
  */
class UsersController @Inject() (usersDao: UsersDao) extends Controller {

  def index = Action.async { implicit request =>
    Future(Ok(views.html.users()))
  }

  val userForm: Form[User] = Form {
    mapping(
      "id" -> ignored(0L),
      "username" -> text,
      "password" -> text,
      "firstName" -> text,
      "lastName" -> text,
      "address" -> text,
      "phoneNumber" -> text,
      "role" -> text
    )(User.apply)(User.unapply)
  }

  def newUser = Action { implicit request =>
    Ok(views.html.new_user())
  }

  def addUser = Action.async { implicit  request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle success.
    val user = userForm.bindFromRequest().get
    usersDao.insert(user.username, user.password, user.firstName, user.lastName, user.address, user.phoneNumber, user.role)
    Future(Redirect(routes.UsersController.newUser()))
  }

  def getUsers = Action.async { implicit request =>
    usersDao.findAll().map { user =>
      Ok(Json.toJson(user))
    }
  }

  def loginUser = Action.async(parse.json) { request =>
    val json = request.body
    val usernameOpt = (json \ "username").asOpt[String]
    val passwordOpt = (json \ "password").asOpt[String]

    (usernameOpt, passwordOpt) match {
      case (Some(username), Some(password)) => {
        for {
          us <- usersDao.findByUsernamePassword(username, password)
        } yield Ok(Json.obj("status" -> "OK", "user" -> us.toJson))
      } recover { case e =>
          BadRequest(Json.obj("status" -> "Failed", "message" -> "Incorrect username/password"))
      }
      case _ => Future(BadRequest(Json.obj("status" -> "Failed", "message" -> "Missing username/password")))
    }
  }
}
