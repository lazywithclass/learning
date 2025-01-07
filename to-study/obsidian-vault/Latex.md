---
cssclasses:
  - cornell-note
tags:
  - latex
---

Mainly stuff I forget about, jotted down at random.

# Sets

$A \cup B$
$A \cap B$
$A \subseteq B$

# Multiple expressions alignment

$$
\begin{align}
&\forall p \in pre(t) \setminus post(t) &\qquad M'(p) &= M(p) - W(\langle p,t \rangle) \\
&\forall p \in post(t) \setminus pre(t) &\qquad M'(p) &= M(p) + W(\langle t,p \rangle) \\
&\forall p \in post(t) \cap pre(t) &\qquad M'(p) &= M(p) - W(\langle p,t \rangle) + W(\langle t,p \rangle) \\
&\forall p \in P \setminus (pre(t) \cup post(t)) &\qquad M'(p) &= M(p)
\end{align}
$$

# Miscellanea

$\displaystyle\sum_{i=0} ^{\infty} a_i x^i$

$\lfloor a \rfloor$

$\lceil a \rceil$

$$
\begin{equation}
    T(x,y) =
    \begin{cases}
      2, & \text{se}\ y=0 \\
      6 + T(x, \lfloor y/2\rfloor) & \text{se} \ y > 0
    \end{cases}
  \end{equation}
$$