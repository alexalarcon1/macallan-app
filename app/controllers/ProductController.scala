package controllers

import com.google.inject.Inject
import dao.{ProductsDao}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class ProductController @Inject() (productDao: ProductsDao) extends Controller {

  def getProducts = Action.async { implicit request =>
    productDao.findAll().map { product =>
      Ok(Json.toJson(product))
    }
  }
}
