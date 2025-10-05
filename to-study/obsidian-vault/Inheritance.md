---
cssclasses:
  - cornell-note
tags:
  - inheritance 
---

Meccanismo che mi permette di stabilire un legame, sottotipo se rispettiamo Liskov, su questo posso costruire [Polimorphism](Polimorphism.md).


Privilegiare composizione sull'ereditarieta' ESPANDERE

> In statically typed languages like C++, one of the key mechanisms that supports abstraction and polymorphism is inheritance. It is by using inheritance that we can create derived classes that conform to the abstract polymorphic interfaces defined by pure virtual functions in abstract base classes.

-- [liskov-substitution-principle](cpp-report_engineering-notebook_liskov-substitution-principle.pdf)

# Favor composition over inheritance

https://softwareengineering.stackexchange.com/questions/65179/where-does-this-concept-of-favor-composition-over-inheritance-come-from

## The Flaws of Inheritance


<iframe width="560" height="315" src="https://www.youtube.com/embed/hxGOiiR9ZKg?si=Cc0Us5Y5zvDpmgmu" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>

Also The Primeagean made a video about it:

<iframe width="560" height="315" src="https://www.youtube.com/embed/HOSdPhAKupw?si=WNQbaQESUKNYzHcs" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

![|300](inheritance-move-up.gif)
"Bundle common elements into a parent class, but as soon as you find an exception to the commonality it requires big changes."

> Change is the enemy of perfect design.
