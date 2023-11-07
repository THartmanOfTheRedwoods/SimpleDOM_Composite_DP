package DOM.traversal;

import DOM.DOMElement;

public interface DOMIterator {
    boolean hasNext();
    DOMElement next();
}
