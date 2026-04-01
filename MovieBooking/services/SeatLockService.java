package services;

import entities.Seat;
import entities.SeatLock;
import entities.Show;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

public class SeatLockService {
    private static final int LOCK_MINUTES = 5;
    private final Map<Show, List<SeatLock>> locksByShow = new HashMap<>();

    public boolean lockSeats(Show show, List<Seat> seats) {
        Set<String> lockedIds = new HashSet<>();
        for (Seat seat : getLockedSeats(show)) {
            lockedIds.add(seat.id);
        }
        for (Seat seat : seats) {
            if (lockedIds.contains(seat.id)) {
                return false;
            }
        }

        SeatLock lock = new SeatLock(show, seats, LocalDateTime.now().plusMinutes(LOCK_MINUTES));
        locksByShow.computeIfAbsent(show, k -> new ArrayList<>()).add(lock);
        return true;
    }

    public void unlockSeats(Show show, List<Seat> seats) {
        List<SeatLock> showLocks = locksByShow.get(show);
        if (showLocks == null) {
            return;
        }

        cleanupExpired(showLocks);
        Iterator<SeatLock> lockIterator = showLocks.iterator();
        while (lockIterator.hasNext()) {
            SeatLock lock = lockIterator.next();
            Set<String> unlockIds = new HashSet<>();
            for (Seat seat : seats) {
                unlockIds.add(seat.id);
            }
            lock.seats.removeIf(s -> unlockIds.contains(s.id));
            if (lock.seats.isEmpty()) {
                lockIterator.remove();
            }
        }
    }

    public List<Seat> getLockedSeats(Show show) {
        List<Seat> result = new ArrayList<>();
        List<SeatLock> showLocks = locksByShow.get(show);
        if (showLocks == null) {
            return result;
        }

        cleanupExpired(showLocks);
        for (SeatLock lock : showLocks) {
            result.addAll(lock.seats);
        }
        return result;
    }

    private void cleanupExpired(List<SeatLock> showLocks) {
        showLocks.removeIf(SeatLock::isExpired);
    }
}
