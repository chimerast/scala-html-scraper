package st.chimera.scraper

import net.htmlparser.jericho._
import st.chimera.scraper.HtmlScraper._

object Main {
  def main(args: Array[String]) {
    val doc = HtmlScraper("http://www.scala-lang.org/")
    doc.eval("div[@class='node']/h2").foreach(_ match {
      case node: Segment =>
        node.eval("text()").foreach(println)
        node.eval("a/@href").foreach(_ match {
          case attr: Attribute => println("  " + attr.getValue)
        })
    })
  }
}
