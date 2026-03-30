import java.util.List;

public class NearestStrategy implements ElevatorStrategy {
    @Override
    public Elevator choose(List<Elevator> elevators, Request request) {
        if (elevators == null || elevators.isEmpty()) {
            return null;
        }

        Elevator best = elevators.get(0);
        int bestDistance = Math.abs(best.getCurrentFloor() - request.getFloor());

        for (int i = 1; i < elevators.size(); i++) {
            Elevator candidate = elevators.get(i);
            int distance = Math.abs(candidate.getCurrentFloor() - request.getFloor());
            if (distance < bestDistance) {
                best = candidate;
                bestDistance = distance;
            }
        }

        return best;
    }
}
