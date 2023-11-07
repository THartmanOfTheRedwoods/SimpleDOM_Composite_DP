package DOM;

import DOM.event.DOMObserver;
import DOM.traversal.DOMIterator;
import DOM.traversal.NullIterator;

import java.util.ArrayList;
import java.util.List;

public class LeafElement implements DOMElement, DOMObserver {
    private final String tagName;
    private List<Attribute> attributes = new ArrayList<>();
    private DOMElement parent;
    private int nestingLevel;
    private List<DOMObserver> observers = new ArrayList<>();

    public LeafElement(String tagName) {
        this.tagName = tagName;
        this.nestingLevel = 0;
    }

    public void addChild(DOMElement element) {
        // Leaf elements cannot have children
    }

    public List<DOMElement> getChildren() {
        return new ArrayList<>(); // Leaf elements have no children
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
        html += String.format("/>%n");
        return html;
    }

    public DOMIterator createIterator() {
        return new NullIterator(); // Leaf elements have no children to iterate
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
            this.nestingLevel = parent.getNestingLevel() + 1;
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
}