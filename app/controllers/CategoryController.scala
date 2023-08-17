package controllers

import controllers.entity.CategoryRepository
import play.api.libs.json.Json
import play.api.mvc.{Action, AnyContent, BaseController, ControllerComponents}

import javax.inject.{Inject, Singleton}

@Singleton
class CategoryController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  private val accessControlHeader = "Access-Control-Allow-Origin"

  def getAllCategories: Action[AnyContent] = Action {
    Ok(Json.toJson(CategoryRepository.getAllCategories())).withHeaders(accessControlHeader -> "*")
  }

  def getCategory(id: Int): Action[AnyContent] = Action {
    Ok(Json.toJson(CategoryRepository.getCategory(id))).withHeaders(accessControlHeader -> "*")
  }

  def updateCategory(id: Int, name: String, description: String): Action[AnyContent] = Action {
    CategoryRepository.updateCategory(id, name, description)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def deleteCategory(id: Int): Action[AnyContent] = Action {
    CategoryRepository.deleteCategory(id)
    Ok.withHeaders(accessControlHeader -> "*")
  }

  def addCategory(name: String, description: String): Action[AnyContent] = Action {
    Ok(Json.toJson(CategoryRepository.addCategory(name, description))).withHeaders(accessControlHeader -> "*")
  }
}
