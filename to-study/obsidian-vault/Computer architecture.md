---
cssclasses:
  - cornell-note
tags:
  - computer-architecture
  - bistable
  - flip-flop
---

## Sequential circuits

Why using sequential circuits?

* store intermediate results in memory
* cut longer circuits in smaller ones with sequential circuits in the middle

![[sequential-circuits.png|900]]

### Bistable

Two retro-activated NOR gates:

![[bistable.png|300]] ![[Pasted image 20240221165152.png|217]]

Once the signal is set (via $S$) it stays in the circuit, so this is the smallest memory that is able to persist 1 bit.<br /> 
$R$ allows to reset the data inside the circuit. $Q$ is the state, $\overline{Q}$ is the state, negated.

![[bistable.gif|300]]

<aside>bistable unknown state</aside>

$S = 1$ and $R = 1$ puts the circuit in an unknown state.

### Clock square wave

^b2c48f

![[clock-square-wave.png|700]]

On a rising edge the level grows from low (logic $0$) to high (logic $1$), on a falling edge the level drops from high to low.

### Async architecture

Pressing $S$et on a bistable would put the state into the circuit.

### Sync architecture

Sync means that the state only changes when the clock allows it.

<aside>Sensitive to levels and edges</aside>

Pressing $S$et on a bistable would put the state into the circuit, only if the clock is at 1.
So in this scenario inputs are processed only if the clock is high, the architecture is said to be sensitive to the level (of the clock).
Being sensitive to the edges means that inputs are listened for when the clock changes values, which is a very brief instant.

Circuit changes when clock:
* high $\rightarrow$ sensitive to the level
* low $\rightarrow$ sensitive to the edge

Bistable sensitive to the level:
![[bistable-level-sensitive.png|700]]

Being sensitive to levels means that a circuit is available for a longer period of time, so in the end it is as if the sequential circuits are not there, because the signal is passing through all of them, as it did with combinatoric circuits.

<aside>Transparency problem</aside>

So the issue is: if a circuit is sensitive to the levels it will wait for a whole duty cycle (see [[#Clock square wave]]), and so if the signal is changes during this "long" time this fluctuation will be propagated forward.

![[architecture-sensitivity-problem.png|900]]

The ides is to have one side of the sequential circuit open, while the other should be closed, this way we avoid the transparency problem, by only allowing the information to enter the circuit, but not to exit from it right away.
So when the clock is high data flows in:
![[avoid-transparency-left-open.png|700]]

and then when the clock is low data flows out:
![[avoid-transparency-right-open.png|700]]

### D latch

A circuit that only has one control, at the bottom of the image it's how it is represented when used withing other circuits

![[d-latch.png|500]]

### Flip-flop

Sensitive to edges. Most important sequential circuit.

Composed of two [[#D latch]] in series. Note how the clock is presented natural to the master while it is presented negated to the slave, this is the open / close mechanism that allows for data to flow in and out at different times.

![[flip-flop.png|700]]

The signal of the clock then goes down suddenly, in that very little time span
* master closes
* slave opens

| Clock | Stage name | Master is | Slave is |
| --- | --- | --- | --- | 
| 1 | flip | opened | isolated |
| 0 | flop | isolated | opened |

<aside>Signal is stable</aside>

The important idea to grasp is that master and slave present stable data as their output, this is because
* when the output gate is open the input is closed, and
* the circuit is combinatoric and as such its output is a function of the input, so since the input doesn't change also the output does not change

State changes only happen during a rising or falling edge (see [[#Clock square wave]]).

So we have two components sensitive to levels, but from the exterior the whole flip flop component is sensitive to edges. So, we have solved the transparency problem.

If we have a combinatoric circuit that takes more that a CPU cycle to perform its task, then we could divide it in chunks and have each of them carry out a single sub-task, all coordinated via sequential circuits.

---

## Single cycle CPU

1 ISA (Instruction Set Architecture) MIPS instruction per 1 clock cycle. Where instruction is a sequence of $0$s and $1$s and represents a requests for a certain task. The CPU knows how to decode this sequence and execute it.

ISA is the language the computer speaks to the compiler.

These are the 5 different phases

![[cpu-phases.png|800]]

### Instruction formats

#### R - register

Instructions that operate on registers.

#### I - immediate

Instructions that operate on costants.

#### J - jump

Instructions for non conditional jumps.

### Memory

Address are aligned, which means they are multiple of $4$, which means the end with $00$.

<aside>endianness</aside>

Based on the endianness, the first byte, the most meaningful one:
* big endian: is be the left-most, or the one with the lower address
* little endian: is the right-most, or the one with the lower address

which dictates where to start reading from.