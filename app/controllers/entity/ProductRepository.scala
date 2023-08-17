package controllers.entity

object ProductRepository {
  private var idCounter = 1

  def addProduct(name: String, cost: Int, categoryId: Int): Product = {
    val product = Product(idCounter, name, cost, categoryId)
    idCounter = idCounter + 1
    Database.products = product :: Database.products
    product
  }

  def deleteProduct(id: Int): Unit = {
    Database.products = Database.products.filter(product => !product.id.equals(id))
  }

  def updateProduct(id: Int, name: String, cost: Int, categoryId: Int): Unit = {
    Database.products = Database.products.map(product => updateProductIfNeeded(product, id, name, cost, categoryId))
  }

  def getAllProduct(): List[Product] = {
    Database.products
  }

  def getProduct(id: Int): Product = {
    for (product <- Database.products) {
      if (product.id.equals(id)) {
        return product
      }
    }
    null
  }

  private def updateProductIfNeeded(product: Product, id: Int, name: String, cost: Int, categoryId: Int): Product = {
    if (product.id.equals(id)) {
      return new Product(id, name, cost, categoryId)
    }
    product
  }
}
