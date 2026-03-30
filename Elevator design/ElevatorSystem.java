import java.util.List;

public class ElevatorSystem {
    private final List<Elevator> elevators;
    private final ElevatorStrategy strategy;

    public ElevatorSystem(List<Elevator> elevators, ElevatorStrategy strategy) {
        this.elevators = elevators;
        this.strategy = strategy;
    }

    public void handleRequest(Request request) {
        Elevator elevator = strategy.choose(elevators, request);
        if (elevator == null) {
            System.out.println("No elevator available for floor " + request.getFloor());
            return;
        }
        System.out.println("Assigned Elevator " + elevator.getId() + " to floor " + request.getFloor());
        elevator.addRequest(request);
    }

    public void step() {
        for (Elevator elevator : elevators) {
            elevator.step();
        }
    }
}
