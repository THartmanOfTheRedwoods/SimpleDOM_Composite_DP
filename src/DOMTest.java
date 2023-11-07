import DOM.CompositeElement;
import DOM.DOMElement;
import DOM.LeafElement;
import DOM.TextElement;
import DOM.event.DOMObserver;
import DOM.transform.DOMBuilder;
import DOM.transform.HTMLParser;
import DOM.traversal.DOMIterator;

import java.util.ArrayList;
import java.util.List;

public class DOMTest {
    public static void main(String[] args) {
        // Create a simple HTML document with attributes and text content
        CompositeElement html = new CompositeElement("html");
        CompositeElement head = new CompositeElement("head");
        CompositeElement body = new CompositeElement("body");

        html.addChild(head);

        CompositeElement titleElement = new CompositeElement("title");
        titleElement.addChild(new TextElement("Document Title"));
        head.addChild(titleElement);

        CompositeElement paragraphElement = new CompositeElement("p");
        paragraphElement.addChild(new TextElement("This is a paragraph #1."));
        body.addChild(paragraphElement);

        LeafElement lineBreak = new LeafElement("br");
        body.addChild(lineBreak);

        paragraphElement = new CompositeElement("p");
        paragraphElement.addChild(new TextElement("This is a paragraph #2."));
        body.addChild(paragraphElement);

        lineBreak = new LeafElement("br");
        body.addChild(lineBreak);

        paragraphElement = new CompositeElement("p");
        paragraphElement.addChild(new TextElement("This is a paragraph #3."));
        body.addChild(paragraphElement);

        LeafElement image = new LeafElement("img");
        image.addAttribute("src", "image.jpg");
        paragraphElement.addChild(image);

        lineBreak = new LeafElement("br");
        body.addChild(lineBreak);

        html.addChild(body);

        // Display the HTML document
        html.display();

        System.out.println("-".repeat(80));

        // Now let's see if we can use the builder pattern to parse this textual html back into our objects.
        DOMElement recreatedRootElement = DOMTest.rebuild(html.getHtml());
        recreatedRootElement.display();

        System.out.println("-".repeat(80));

        // Traverse full DOM using iterator
        DOMIterator iterator = html.createIterator();
        while (iterator.hasNext()) {
            DOMElement element = iterator.next();
            if (element != null) {
                System.out.println("Traversed: " + element.getElementName());
            }
        }

        System.out.println("-".repeat(80));

        // Find all elements with the tag name "p"
        List<DOMElement> foundElements = findElementsByTagName(html, "p");
        System.out.println("Found " + foundElements.size() + " elements with tag name 'p'");
        System.out.println(foundElements.toString());
    }

    // Method to find all elements with a specific tag name using DOMIterator
    public static List<DOMElement> findElementsByTagName(DOMElement rootElement, String tagName) {
        List<DOMElement> foundElements = new ArrayList<>();
        DOMIterator iterator = rootElement.createIterator();
        while (iterator.hasNext()) {
            DOMElement element = iterator.next();
            if (element != null && element.getElementName().equals(tagName)) {
                foundElements.add(element);
            }
        }
        return foundElements;
    }

    public static DOMElement rebuild(String html) {
        DOMBuilder builder = new DOMBuilder();
        HTMLParser parser = new HTMLParser(html, builder);
        parser.parse();

        return builder.build();
    }
}