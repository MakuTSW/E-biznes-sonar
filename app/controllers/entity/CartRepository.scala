package controllers.entity

object CartRepository {
  private var idCounter = 1

  def createCart(): Cart = {
    val cart = Cart(idCounter, List.empty)
    idCounter = idCounter + 1
    Database.carts = cart :: Database.carts
    cart
  }

  def deleteCart(id: Int): Unit = {
    Database.carts = Database.carts.filter(cart => !cart.id.equals(id))
  }

  def deleteProductFromCart(id: Int, productId: Int): Unit = {
    Database.carts = Database.carts.map(cart => deleteProductFromCart(cart, id, productId))
  }

  def addProductToCart(id: Int, productId: Int): Unit = {
    Database.carts = Database.carts.map(cart => addProductToCart(cart, id, productId))
  }

  def getAllCarts(): List[Cart] = {
    Database.carts
  }

  def getCart(id: Int): Cart = {
    for (cart <- Database.carts) {
      if (cart.id.equals(id)) {
        return cart
      }
    }
    null
  }

  private def addProductToCart(cart: Cart, id: Int, productId: Int): Cart = {
    if (cart.id.equals(id)) {
      return new Cart(id, productId :: cart.productIds)
    }
    cart
  }

  private def deleteProductFromCart(cart: Cart, id: Int, productId: Int): Cart = {
    if (cart.id.equals(id)) {
      return new Cart(id, cart.productIds.filter(pId => !pId.equals(productId)))
    }
    cart
  }
}
