package plecko.infrastructure

import com.typesafe.config.Config

object Feed {
  def aFeedFrom(feedConfig: Config): Feed = {
    new Feed(feedConfig.getString("name"), feedConfig.getString("url"))
  }
}

class Feed(val name: String, val url: String) {

  override def toString(): String = s"Feed<$name, $url>"
}
