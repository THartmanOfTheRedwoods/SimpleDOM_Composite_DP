package DOM.traversal;

import DOM.DOMElement;
public class CompositeIterator implements DOMIterator {
    private DOMElement compositeElement;
    private int position = 0;
    private DOMIterator subIterator;

    public CompositeIterator(DOMElement element) {
        this.compositeElement = element;
        if (!compositeElement.getChildren().isEmpty()) {
            subIterator = compositeElement.getChildren().get(0).createIterator();
        }
    }

    public boolean hasNext() {
        return position < compositeElement.getChildren().size() || (subIterator != null && subIterator.hasNext());
    }

    public DOMElement next() {
        if (subIterator != null && subIterator.hasNext()) {
            return subIterator.next();
        }
        if (this.hasNext()) {
            if (position < compositeElement.getChildren().size()) {
                DOMElement element = compositeElement.getChildren().get(position);
                subIterator = element.createIterator();
                position++;
                return element;
            }
        }
        return null;
    }
}