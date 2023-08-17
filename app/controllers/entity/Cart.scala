package controllers.entity

import play.api.libs.json.{Json, OFormat}

case class Cart(id: Int, productIds: List[Int])

object Cart {
  implicit val format: OFormat[Cart] = Json.format[Cart]
}