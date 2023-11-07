package DOM.transform;

import DOM.CompositeElement;
import DOM.DOMElement;
import DOM.TextElement;

import java.util.Stack;

public class DOMBuilder {
    private CompositeElement rootElement;
    private Stack<CompositeElement> elementStack = new Stack<>();
    private CompositeElement currentElement;

    public DOMElement build() {
        return rootElement;
    }

    public void startElement(String tagName) {
        CompositeElement element = new CompositeElement(tagName);
        if (currentElement == null) {
            rootElement = element;
        } else {
            currentElement.addChild(element);
        }
        elementStack.push(currentElement);
        currentElement = element;
    }

    public void endElement() {
        if (!elementStack.isEmpty()) {
            currentElement = elementStack.pop();
        }
    }

    public void setAttribute(String name, String value) {
        if (currentElement != null) {
            currentElement.addAttribute(name, value);
        }
    }

    public void addText(String text) {
        if (currentElement != null) {
            TextElement textElement = new TextElement(text);
            currentElement.addChild(textElement);
        }
    }


}

