package DOM;

import DOM.event.DOMObserver;
import DOM.traversal.CompositeIterator;
import DOM.traversal.DOMIterator;

import java.util.ArrayList;
import java.util.List;

public class CompositeElement implements DOMElement, DOMObserver {
    private final String tagName;
    private List<DOMElement> children = new ArrayList<>();
    private List<Attribute> attributes = new ArrayList<>();
    private DOMElement parent;
    private int nestingLevel;
    private List<DOMObserver> observers = new ArrayList<>();

    public CompositeElement(String tagName) {
        this.tagName = tagName;
        this.nestingLevel = 0;
    }

    public void addChild(DOMElement element) {
        element.setParent(this); // Set the parent of the child element
        children.add(element);
        observers.add((DOMObserver)element);
    }

    public List<DOMElement> getChildren() {
        return children;
    }

    public void addAttribute(String name, String value) {
        attributes.add(new Attribute(name, value));
    }

    public void display() {
        System.out.println(getHtml());
    }

    public String getHtml() {
        String indent = "    ".repeat(nestingLevel);
        String html = indent + "<" + tagName;
        for (Attribute attribute : attributes) {
            html += " " + attribute.name + "='" + attribute.value + "'";
        }
        html += String.format(">%n");
        for (DOMElement child : children) {
            html += child.getHtml();
        }
        html += String.format("%s</%s>%n", indent, tagName);
        return html;
    }

    public DOMIterator createIterator() {
        return new CompositeIterator(this);
    }

    public String getElementName() {
        return tagName;
    }

    public DOMElement getParent() {
        return parent;
    }

    public void setParent(DOMElement parent) {
        this.parent = parent;
        if (parent != null) {
            this.setNestingLevel(parent.getNestingLevel() + 1);
        }
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public void setNestingLevel(int nestingLevel) {
        this.nestingLevel = nestingLevel;
        notifyObservers();
    }

    public void addObserver(DOMObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(DOMObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (DOMObserver observer : observers) {
            observer.updateNestingLevel(nestingLevel);
        }
    }

    @Override
    public void updateNestingLevel(int newNestingLevel) {
        setNestingLevel(newNestingLevel+1);
    }

    @Override
    public String toString() {
        return String.format("""
                Level: %d, Tag: <%s>, Child Count: %d%n
                """, nestingLevel, tagName, this.getChildren().size());
    }
}