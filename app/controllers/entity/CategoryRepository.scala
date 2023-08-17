package controllers.entity

object CategoryRepository {
  private var idCounter = 1

  def addCategory(name: String, description: String): Category = {
    val category = Category(idCounter, name, description)
    idCounter = idCounter + 1
    Database.categories = category :: Database.categories
    category
  }

  def deleteCategory(id: Int): Unit = {
    Database.categories = Database.categories.filter(category => !category.id.equals(id))
  }

  def updateCategory(id: Int, name: String, description: String): Unit = {
    Database.categories = Database.categories.map(category => updateCategoryIfNeeded(category, id, name, description))
  }

  def getAllCategories(): List[Category] = {
    Database.categories
  }

  def getCategory(id: Int): Category = {
    for (category <- Database.categories) {
      if (category.id.equals(id)) {
        return category
      }
    }
    null
  }

  private def updateCategoryIfNeeded(category: Category, id: Int, name: String, description: String): Category = {
    if (category.id.equals(id)) {
      return new Category(id, name, description)
    }
    category
  }
}
