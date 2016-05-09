package controllers

import javax.inject.Inject

import dao.OrderedProductsDao
import play.mvc.Controller

/**
  * Created by alex_alarcon on 5/8/2016.
  */
class OrderedProductsController @Inject()(orderedProductsDao: OrderedProductsDao) extends Controller {


}
