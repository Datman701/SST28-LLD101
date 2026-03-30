# Elevator System (Minimal LLD)

A compact Java implementation of a multi-elevator system using simple object-oriented design and one strategy (`NearestStrategy`).

## What It Supports

- Multiple elevators and multiple floors
- External floor buttons (`UP` / `DOWN`)
- Internal elevator buttons
- Request assignment through strategy
- Per-elevator request queue (`Queue<Request>` via `LinkedList`)
- Door open/close behavior
- Weight-limit check before door close
- Step-based simulation loop (`step()`)

## Design Summary

### Core Objects

- `ElevatorSystem`
  - Owns all elevators and the strategy
  - Accepts incoming requests and assigns an elevator
  - Advances all elevators in each simulation tick

- `Elevator`
  - Holds current floor, direction, door, panel, weight sensor, and request queue
  - Processes one simulation step at a time
  - Moves toward next request floor and opens door on arrival
  - Tries to close door at step start; keeps it open if overweight

- `ElevatorPanel`
  - Lives inside an elevator
  - Contains internal floor buttons and open/close/alarm buttons
  - Delegates user actions to elevator behavior

- `Floor`
  - Contains external buttons (`UP`, `DOWN`)

- `Request`
  - Captures target floor, direction, and type (`INTERNAL`, `EXTERNAL`)

- `Door`
  - Simple state holder with `open()`, `close()`, `isOpen()`

- `WeightSensor`
  - Tracks `maxWeight` and `currentWeight`
  - Exposes `isOverWeight()`

- `ElevatorStrategy` + `NearestStrategy`
  - Chooses elevator with minimum floor distance from request
  - Intentionally simple (no complex optimization)

## Request Flow

### External Request

1. `ExternalButton.press()`
2. `ElevatorSystem.handleRequest(request)`
3. `strategy.choose(elevators, request)`
4. Selected elevator gets `addRequest(request)`

### Internal Request

1. `InternalButton.press()`
2. `ElevatorPanel.requestFloor(floor)`
3. Elevator gets `addRequest(request)`

## Elevator Step Logic

Each call to `Elevator.step()`:

1. If door is open, try to close it
2. If overweight, keep door open and stop this step
3. If queue is empty, set direction to `IDLE`
4. Otherwise read next request
5. If already at target floor, open door and dequeue request
6. Else move one floor toward target

## Run

From this folder:

```bash
javac *.java
java Main
```

`Main` creates 2 elevators, 10 floors, simulates a few button presses, and runs the system in a `while(true)` loop printing movement and door logs.
