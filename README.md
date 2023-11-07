# Composite and Iterator Pattern Demo

```mermaid
classDiagram
  class DOMElement {
    <<interface>>
    +display()
    +getHtml()
    +addChild(element: DOMElement): void
    +getChildren(): List<DOMElement>
    +createIterator(): DOMIterator
    +getElementName(): String
    +getParent(): DOMElement
    +setParent(parent: DOMElement): void
    +getNestingLevel(): int
    +updateNestingLevel(newNestingLevel: int): void
    +addObserver(observer: DOMObserver): void
    +removeObserver(observer: DOMObserver): void
    +notifyObservers(): void
  }
  class DOMObserver {
    <<interface>>
    +updateNestingLevel(newNestingLevel: int): void
  }
  class CompositeElement {
    -tagName: String
    -children: List<DOMElement>
    -attributes: List<Attribute>
    -parent: DOMElement
    -nestingLevel: int
    -observers: List<DOMObserver>
    +addChild(element: DOMElement): void
    +getChildren(): List<DOMElement>
    +addAttribute(name: String, value: String): void
    +display(): void
    +getHtml(): String
    +createIterator(): DOMIterator
    +getElementName(): String
    +getParent(): DOMElement
    +setParent(parent: DOMElement): void
    +getNestingLevel(): int
    +setNestingLevel(nestingLevel: int): void
    +addObserver(observer: DOMObserver): void
    +removeObserver(observer: DOMObserver): void
    +notifyObservers(): void
    +updateNestingLevel(newNestingLevel: int): void
  }
  class LeafElement {
    -tagName: String
    -attributes: List<Attribute>
    -parent: DOMElement
    -nestingLevel: int
    -observers: List<DOMObserver>
    +addChild(element: DOMElement): void
    +getChildren(): List<DOMElement>
    +addAttribute(name: String, value: String): void
    +display(): void
    +getHtml(): String
    +createIterator(): DOMIterator
    +getElementName(): String
    +getParent(): DOMElement
    +setParent(parent: DOMElement): void
    +getNestingLevel(): int
    +setNestingLevel(nestingLevel: int): void
    +addObserver(observer: DOMObserver): void
    +removeObserver(observer: DOMObserver): void
    +notifyObservers(): void
    +updateNestingLevel(newNestingLevel: int): void
  }
  class TextElement {
    -textContent: String
    -parent: DOMElement
    -nestingLevel: int
    -observers: List<DOMObserver>
    +addChild(element: DOMElement): void
    +getChildren(): List<DOMElement>
    +addAttribute(name: String, value: String): void
    +display(): void
    +getHtml(): String
    +createIterator(): DOMIterator
    +getElementName(): String
    +getParent(): DOMElement
    +setParent(parent: DOMElement): void
    +getNestingLevel(): int
    +setNestingLevel(nestingLevel: int): void
    +addObserver(observer: DOMObserver): void
    +removeObserver(observer: DOMObserver): void
    +notifyObservers(): void
    +updateNestingLevel(newNestingLevel: int): void
  }
  class CompositeIterator {
    -compositeElement: DOMElement
    -position: int
    -subIterator: DOMIterator
    +hasNext(): boolean
    +next(): DOMElement
  }
  class NullIterator {
    +hasNext(): boolean
    +next(): DOMElement
  }
  class DOMIterator {
    <<interface>>
    +hasNext(): boolean
    +next(): DOMElement
  }

  DOMElement <|-- CompositeElement : implements
  DOMElement <|-- LeafElement : implements
  DOMElement <|-- TextElement : implements
  DOMObserver <|-- CompositeElement : implements
  DOMObserver <|-- LeafElement : implements
  DOMObserver <|-- TextElement : implements
  DOMIterator <|-- CompositeIterator : implements
  DOMIterator <|-- NullIterator : implements
  LeafElement --> NullIterator
  TextElement --> NullIterator
  CompositeElement --> CompositeIterator
  CompositeElement *-- DOMElement
```

## Bonus Builder Design Pattern

* Found in DOM.transform.DOMBuilder
* HTMLParse is just a generic use of org.jsoup, but we could build our own lexical analyzer and parser with something like org.antlr if we wanted to extend this demonstration.
