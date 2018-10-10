package plecko.domain.rss

case class Item(val link: String, val title: String, val content: String) {
  override def toString: String = s"$link item<$title, ${content.length}>"
}
