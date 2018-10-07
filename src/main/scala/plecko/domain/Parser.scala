package plecko.domain

import plecko.domain.rss.Item

trait Parser {
  def parse(path: String):Seq[Item]
}
