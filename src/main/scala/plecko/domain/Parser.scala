package plecko.domain

trait Parser {
  def parse(path: String):Seq[Item]
}
