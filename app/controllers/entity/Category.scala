package controllers.entity

import play.api.libs.json.{Json, OFormat}

case class Category(id: Int, name: String, description: String)

object Category {
  implicit val format: OFormat[Category] = Json.format[Category]
}