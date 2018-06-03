package plecko.domain

class Item(val title: String, val content: String) {
  override def toString: String = s"item<$title, ${content.length}>"
}
