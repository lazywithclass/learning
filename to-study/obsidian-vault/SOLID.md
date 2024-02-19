## Single Responsibility Principle
 

The S of SOLID.

  

"Each software module should have one and only one reason to change", but then "What defines a reason to change?".

  

"This principle is about people."

  

[...]

  

This is the reason we do not put SQL in JSPs. This is the reason we do not generate HTML in the modules that compute results. This is the reason that business rules should not know the database schema. This is the reason we separate concerns."

  

"Gather together the things that change for the same reasons. Separate those things that change for different reasons."

  

["A good system should be high on cohesion and low on coupling"](https://www.enjoyalgorithms.com/blog/single-responsibility-principle-in-oops)

  

![img](https://cdn-images-1.medium.com/max/640/0*11cZSBOkR82nknwy.png)

  

### In web development

  

We have multi-tier architectures that allow different people to work on different parts of the application: frontend, backend, and database for example. A bug fix

in the backend code should not affect the other two parts.

  
  

### KWIC index exercise

  

In [the whitepaper mentioned in Uncle Bob's article](https://dl.acm.org/doi/pdf/10.1145/361598.361623) the author studies the problem using this production system.

  

"The KWIC index system accepts an ordered set of lines, each line is an ordered set of words,

and each word is an ordered set of characters. Any line may be "circularly shifted" by repeatedly removing the

first word and appending it at the end of the line. The KWIC index system outputs a listing of all circular shifts

of all lines in alphabetical order."

  

### A personal note

  

Feels to me that the S in SOLID is extremely more important than the other "letters", and way more difficult to explain and reason with. IMHO SOLID should be

more something like S-and-olid. Or something more clever than this silly name I've come up.



## Dependency inversion

This is pure gold: https://blog.cleancoder.com/uncle-bob/2016/01/04/ALittleArchitecture.html
