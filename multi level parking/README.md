# Multilevel Parking Lot

Simple Java implementation of a multilevel parking lot.

## Features
- Multiple levels and slots
- Slot types: SMALL, MEDIUM, LARGE
- Nearest-slot allocation strategy
- Ticket at entry, pricing at exit
- Atomic slot reservation using `AtomicBoolean`

## Run
From this folder:

```bash
javac Main.java parking/*.java slot/*.java strategy/*.java ticket/*.java vehicle/*.java gate/*.java
java Main
```
