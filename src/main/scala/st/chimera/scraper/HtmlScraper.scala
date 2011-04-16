package st.chimera.scraper

import net.htmlparser.jericho._
import org.jaxen.jericho._
import scala.collection.JavaConversions._

class HtmlScraper(doc: Source) {
  def eval(xpath: String): List[AnyRef] = {
    new JerichoXPath(xpath).evaluate(doc) match {
      case list: java.util.List[AnyRef] =>
        list.toList
      case elem =>
        List(elem)
    }
  }
}

object HtmlScraper {
  def apply(url: String, args: Any*): HtmlScraper = {
    val navigator = DocumentNavigator.getInstance
    val doc = navigator.getDocument(url.format(args: _*))
    new HtmlScraper(doc.asInstanceOf[Source])
  }

  def parse(text: CharSequence): HtmlScraper = {
    val doc = new Source(text)
    doc.fullSequentialParse
    new HtmlScraper(doc)
  }
}
