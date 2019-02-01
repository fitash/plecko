package plecko.infrastructure

import com.typesafe.config.Config

import scala.concurrent.duration.Duration

object FeedDefinition {
  def aFeedFrom(feedConfig: Config): FeedDefinition = {
    new FeedDefinition(
      feedConfig.getString("name"),
      feedConfig.getString("url"),
      Duration(feedConfig.getString("frequency")),
        feedConfig.getInt("numberOfTrials"))
  }
}

class FeedDefinition(val name: String, val url: String, val frequency: Duration, val numberOfTrials: Int) {

  override def toString(): String = s"Feed<$name, $url, $frequency, $numberOfTrials>"
}
