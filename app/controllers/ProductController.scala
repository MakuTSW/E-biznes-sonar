package controllers

import controllers.entity.ProductRepository

import javax.inject._
import play.api.libs.json.Json
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class ProductController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val accessControlHeader = "Access-Control-Allow-Origin"

  def getAllProduct: Action[AnyContent] = Action {
    Ok(Json.toJson(ProductRepository.getAllProduct())).withHeaders(accessControlHeader -> "*")
  }

  def getProduct(id : Int): Action[AnyContent] = Action {
    Ok(Json.toJson(ProductRepository.getProduct(id))).withHeaders(accessControlHeader -> "*")
  }

  def updateProduct(id: Int, name: String, cost: Int, categoryId: Int): Action[AnyContent] = Action {
    ProductRepository.updateProduct(id, name, cost, categoryId)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def deleteProduct(id: Int): Action[AnyContent] = Action {
    ProductRepository.deleteProduct(id)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def addProduct(name: String, cost: Int, categoryId: Int): Action[AnyContent] = Action {
    Ok(Json.toJson(ProductRepository.addProduct(name, cost, categoryId))).withHeaders(accessControlHeader -> "*")
  }
}
