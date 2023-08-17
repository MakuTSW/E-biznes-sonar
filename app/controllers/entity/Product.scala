package controllers.entity

import play.api.libs.json.{Json, OFormat}

case class Product(id: Int, name: String, cost: Int, categoryId: Int)

object Product {
  implicit val format: OFormat[Product] = Json.format[Product]
}