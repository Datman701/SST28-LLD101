# Pen Design (Simple Implementation)

This is a very simple Java implementation of the provided pen design UML.

## What is implemented

- `Pen` as an abstract base class
- `OpenStrategy` interface with two implementations:
  - `CapStrategy`
  - `ClickStrategy`
- `RefillStrategy` interface with three default implementations:
  - `GelRefillStrategy`
  - `InkRefillStrategy`
  - `BallRefillStrategy`
- `Refill` model holding refill details
- Concrete pens:
  - `GelPen`
  - `InkPen`
  - `BallPen`
- `PenFactory` with one creation method:
  - `getPen(penType, color, inkType, refillStrategy)`
  - pass `null` as `refillStrategy` to use default strategy by pen type
- `Main` for quick demo

## Design mapping

- Strategy pattern for opening/closing behavior and refill behavior
- Factory pattern for centralized pen creation
- Inheritance via abstract `Pen` and concrete pen types
- Composition where `Pen` holds `OpenStrategy`, `RefillStrategy`, and `Refill`

`RefillStrategy` is kept as `refill(Refill refill)` to keep coupling low.
If required later, it can be changed to receive full pen context.

## Run

From `Pen Design/src`:

```bash
javac *.java
java Main
```
