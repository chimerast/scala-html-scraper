package st.chimera.scraper

object Main {
  def main(args: Array[String]) {
    val url = "http://www.scala-lang.org/"
    HtmlScraper(url).eval("div[@class='node']/h2/text()").foreach(println)
  }
}
