package com.example.elevator.strategy;

import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServiceElevatorStrategy implements DispatchStrategy {

    private static final Logger log = LoggerFactory.getLogger(ServiceElevatorStrategy.class);

    private final int totalFloors;
    private final int elevatorCount;

    public ServiceElevatorStrategy(int totalFloors, int elevatorCount) {
        this.totalFloors = totalFloors;
        this.elevatorCount = elevatorCount;
    }

    @Override
    public Elevator assignElevator(List<Elevator> elevators, FloorRequest request) {

        List<Elevator> serviceElevators = elevators.stream()
                .filter(Elevator::isServiceElevator)
                .toList();

        Elevator assigned = serviceElevators.isEmpty()
                ? elevators.stream().findFirst().orElse(null)
                : serviceElevators.get(0);

        if (assigned == null) {
            log.warn("service_strategy=none_available src={} dst={}",
                    request.getSourceFloor(), request.getDestinationFloor());
            return null;
        }

        assigned.addRequest(request);

        log.info("service_strategy=assigned elevatorId={} src={} dst={}",
                assigned.getId(), request.getSourceFloor(), request.getDestinationFloor());

        return assigned;
    }
}
