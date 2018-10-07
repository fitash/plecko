package plecko.domain.rss

case class Item(val url: String,val title: String, val content: String) {
  override def toString: String = s"$url item<$title, ${content.length}>"
}
