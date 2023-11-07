package DOM;

import DOM.event.DOMObserver;
import DOM.traversal.DOMIterator;
import DOM.traversal.NullIterator;

import java.util.ArrayList;
import java.util.List;

public class TextElement implements DOMElement, DOMObserver {
    private String textContent;
    private DOMElement parent;
    private int nestingLevel;
    private List<DOMObserver> observers = new ArrayList<>();

    public TextElement(String textContent) {
        this.textContent = textContent;
        this.nestingLevel = 0;
    }

    public void addChild(DOMElement element) {
        // Text elements cannot have children
    }

    public List<DOMElement> getChildren() {
        return new ArrayList<>(); // Text elements have no children
    }

    public void display() {
        System.out.println(getHtml());
    }
    public String getHtml() {
        String indent = "    ".repeat(nestingLevel);
        return String.format("%s%s%n", indent, textContent);
    }

    public DOMIterator createIterator() {
        return new NullIterator(); // Text elements have no children to iterate
    }

    public String getElementName() {
        return "[TEXT]"; // Text elements do not have tag names
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