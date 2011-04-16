package st.chimera.scraper

import net.htmlparser.jericho._
import org.jaxen.jericho._
import scala.collection.JavaConversions._

class HtmlScraper(segment: Segment) {
  def eval(xpath: String): List[AnyRef] = {
    new JerichoXPath(xpath).evaluate(segment) match {
      case list: java.util.List[AnyRef] =>
        list.toList
      case elem =>
        List(elem)
    }
  }
}

object HtmlScraper {
  def apply(url: String, args: Any*): Segment = {
    DocumentNavigator.getInstance.getDocument(url.format(args: _*)).asInstanceOf[Source]
  }

  def parse(text: CharSequence): Segment = {
    val doc = new Source(text)
    doc.fullSequentialParse
    doc
  }

  implicit def segmentWrapper(segment: Segment) = new HtmlScraper(segment)
}
