package DOM.traversal;

import DOM.DOMElement;

public class NullIterator implements DOMIterator {
    public boolean hasNext() {
        return false;
    }

    public DOMElement next() {
        return null;
    }
}
