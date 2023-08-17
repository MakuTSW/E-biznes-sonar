package controllers

import controllers.entity.CartRepository
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents, Result}

import javax.inject.{Inject, Singleton}

@Singleton
class CartController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def options(path: String): Action[AnyContent] = Action { request =>
    NoContent.withHeaders(
      "Access-Control-Allow-Origin" -> "*",
      "Access-Control-Allow-Methods" -> "GET, POST, PUT, DELETE, OPTIONS",
      "Access-Control-Allow-Headers" -> "Origin, X-Requested-With, Content-Type, Accept"
    )
  }

  def getAllCarts: Action[AnyContent] = Action {
    Ok(Json.toJson(CartRepository.getAllCarts())).withHeaders("Access-Control-Allow-Origin" -> "*")
  }

  def getCart(id: Int): Action[AnyContent] = Action {
    val cart = CartRepository.getCart(id)
    if (cart == null) {
      NotFound.withHeaders("Access-Control-Allow-Origin" -> "*")
    } else {
      Ok(Json.toJson(cart)).withHeaders("Access-Control-Allow-Origin" -> "*")
    }
  }

  def addProductToCart(id: Int, productId: Int): Action[AnyContent] = Action {
    CartRepository.addProductToCart(id, productId)
    Ok.withHeaders("Access-Control-Allow-Origin" -> "*")
  }

  def deleteCart(id: Int): Action[AnyContent] = Action {
    CartRepository.deleteCart(id)
    Ok.withHeaders("Access-Control-Allow-Origin" -> "*")
  }

  def createCart(): Action[AnyContent] = Action {
    Ok(Json.toJson(CartRepository.createCart())).withHeaders("Access-Control-Allow-Origin" -> "*")
  }

  def deleteProductFromCart(id: Int, productId: Int): Action[AnyContent] = Action {
    CartRepository.deleteProductFromCart(id, productId)
    Ok.withHeaders("Access-Control-Allow-Origin" -> "*")
  }
}
