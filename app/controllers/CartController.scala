package controllers

import controllers.entity.CartRepository
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Result}

import javax.inject.{Inject, Singleton}

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val accessControlHeader = "Access-Control-Allow-Origin"

  def options(path: String): Action[AnyContent] = Action { request =>
    NoContent.withHeaders(
      accessControlHeader -> "*",
      "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept"
    )
  }

  def getAllCarts: Action[AnyContent] = Action {
    Ok(Json.toJson(CartRepository.getAllCarts())).withHeaders(accessControlHeader -> "*")
  }

  def getCart(id: Int): Action[AnyContent] = Action {
    val cart = CartRepository.getCart(id)
    if (cart == null) {
      NotFound.withHeaders(accessControlHeader -> "*")
    } else {
      Ok(Json.toJson(cart)).withHeaders(accessControlHeader -> "*")
    }
  }

  def addProductToCart(id: Int, productId: Int): Action[AnyContent] = Action {
    CartRepository.addProductToCart(id, productId)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def deleteCart(id: Int): Action[AnyContent] = Action {
    CartRepository.deleteCart(id)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def createCart(): Action[AnyContent] = Action {
    Ok(Json.toJson(CartRepository.createCart())).withHeaders(accessControlHeader -> "*")
  }

  def deleteProductFromCart(id: Int, productId: Int): Action[AnyContent] = Action {
    CartRepository.deleteProductFromCart(id, productId)
    Ok.withHeaders(accessControlHeader -> "*")
  }
}
