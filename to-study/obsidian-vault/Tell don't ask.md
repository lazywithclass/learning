---
cssclasses: []
tags:
  - sweng
---
>To ask is a query, to tell is a command

 This principle concerns how objects interact, specifically regarding state and behavior. It emphasizes telling an object what to do, rather than asking it for its state and then making decisions based on that state externally.

 Rule: instead of querying an object's state and then performing actions based on that state in the calling code, you should delegate that responsibility to the object itself by providing methods that encapsulate the action and the decision-making logic.
 
Goal: to improve encapsulation and place behavior closer to the data it operates on. This makes objects more autonomous and responsible for their own state and the rules governing it.

## Example

Asking
```java
if (account.getBalance() > withdrawalAmount) { 
	account.debit(withdrawalAmount); 
}
```

Telling:
```java
account.withdraw(withdrawalAmount);
```

The logic `if (balance > amount)` is inside the `withdraw` method of the `Account` class.

## Difference with [Law of Demeter](Law%20of%20Demeter.md)

Following "Tell, Don't Ask" often helps you adhere to the "Principle of Least Knowledge." When you tell an object to perform an operation (`account.withdraw(amount)`), you don't need to ask for its internal details (`account.getBalance()`), which might involve navigating through other objects, potentially violating LoD.

LoD is primarily about the structure of collaborations (who talks to whom), while TDA is about the nature of the interaction (commands vs. queries+decisions).

LoD restricts the scope of interactions, while TDA guides the style of interaction towards commands rather than state queries. Both contribute to building more robust, maintainable, and less coupled object-oriented systems.

## Martin Fowler

> For me, tell-don't-ask is a stepping stone towards co-locating behavior and data, but I don't find it a point worth highlighting.

-- https://martinfowler.com/bliki/TellDontAsk.html

Follow the links in the article because there's more to be learned.