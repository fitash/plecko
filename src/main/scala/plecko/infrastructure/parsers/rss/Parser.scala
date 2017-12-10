package plecko.infrastructure.parsers.rss

object Parser {
  def main(args: Array[String]): Unit = {
    val test =
      <test>
        <testChild anAttribute="attributeValue">something</testChild>
        <testChild anAttribute="attributeValue2">
          <a>
            <deep anAttribute="anAttributeValue">something deeep!</deep>
          </a>
        </testChild>
      </test>
    for (x <- (test \ "testChild")) {
      println("->" + (x \\ "deep" \ "@anAttribute"))
    }

    
  }
}

class Parser {

}
