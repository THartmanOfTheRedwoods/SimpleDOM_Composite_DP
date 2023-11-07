package DOM.transform;

import DOM.DOMElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

public class HTMLParser {
    private String html;
    private DOMBuilder builder;

    public HTMLParser(String html, DOMBuilder builder) {
        this.html = html;
        this.builder = builder;
    }

    public void parse() {
        Document document = Jsoup.parse(html);
        Element root = document.child(0); // Assuming <html> is the root element

        buildDOMStructure(root);
    }

    private void buildDOMStructure(Element element) {
        builder.startElement(element.tagName());

        for (Node child : element.childNodes()) {
            if (child instanceof TextNode) {
                // Capture and set text content
                String text = ((TextNode) child).getWholeText().trim();
                if (!text.isEmpty()) {
                    builder.addText(text);
                }
            } else if (child instanceof Element) {
                buildDOMStructure((Element) child); // Recursively process child elements
            }
        }

        builder.endElement();
    }
}
