# MovieBooking (Minimal BookMyShow LLD)

A small, interview-friendly Java implementation of BookMyShow low-level design.

## What it includes

- In-memory data only (no database)
- Simple entities and services
- User APIs:
  - showMovies(city)
  - showTheatres(city)
  - bookTicket(showId, seats)
- Admin APIs:
  - addCity(city)
  - addTheatre(theatre)
  - addMovie(movie)
  - addShow(show)

## Booking flow

1. Lock seats
2. Calculate price
3. Make payment
4. Confirm booking
5. Release lock

If payment fails, seats are unlocked.

## Seat availability rule

Available seats = all seats - locked seats - booked seats

- Locked seats are managed by SeatLockService
- Booked seats are managed by BookingService

## Run

Compile:

javac Main.java entities/*.java services/*.java

Run:

java Main

## Notes

- Seat locks expire after 5 minutes
- PaymentService is mocked and returns SUCCESS
- No threads, schedulers, REST APIs, auth, or concurrency handling
