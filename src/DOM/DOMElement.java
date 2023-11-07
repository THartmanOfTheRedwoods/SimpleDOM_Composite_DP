package DOM;

import DOM.event.DOMObserver;
import DOM.traversal.DOMIterator;

import java.util.List;

public interface DOMElement {
    void display();
    String getHtml();
    void addChild(DOMElement element);
    List<DOMElement> getChildren();
    DOMIterator createIterator();
    String getElementName();
    DOMElement getParent();
    void setParent(DOMElement parent);
    int getNestingLevel();
    void updateNestingLevel(int newNestingLevel);
    void addObserver(DOMObserver observer);
    void removeObserver(DOMObserver observer);
    void notifyObservers();
}