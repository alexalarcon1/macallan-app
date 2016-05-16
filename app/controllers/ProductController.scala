package controllers

import com.google.inject.Inject
import dao.ProductsDao
import models.{Product, User}
import play.api.data.Forms._
import play.api.data.{Form, Forms}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import play.api.data.format.Formats._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class ProductController @Inject() (productDao: ProductsDao) extends Controller {

  val productForm: Form[Product] = Form {
    mapping(
      "id" -> ignored(0L),
      "name" -> text,
      "brand" -> text,
      "price" -> of(doubleFormat),
      "size" -> of(doubleFormat),
      "kind" -> text,
      "quantity" -> longNumber,
      "percentage" -> of(doubleFormat),
      "origin" -> text,
      "status" -> text
    )(Product.apply)(Product.unapply)
  }

  def newProduct = Action { implicit request =>
    Ok(views.html.new_product())
  }

  def getProducts = Action.async { implicit request =>
    productDao.findAll().map { product =>
      Ok(Json.toJson(product))
    }
  }

  def addProduct = Action.async { implicit  request =>
    // Bind the form first, then fold the result, passing a function to handle errors, and a function to handle success.
    val prod = productForm.bindFromRequest().get
    productDao.insert(prod.name, prod.brand, prod.price, prod.size, prod.kind, prod.quantity, prod.percentage, prod.origin, prod.status)
    Future(Redirect(routes.ProductController.newProduct()).flashing("success" -> "Product was added"))
  }
}
