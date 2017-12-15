package plecko.infrastructure.parsers.rss

import org.scalatest.FunSuite

class ParserTest extends FunSuite {
  test("parses feed with no items") {
    assert(true)
  }

  test("parses feed items") {
    val path: String = Parser.getClass.getResource("microsiervos.xml").getPath
    val parser = new Parser()
    val items = parser.parse(path)
    assert(items.length == 15)
  }
}

