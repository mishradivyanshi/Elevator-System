package com.example.elevator.strategy;

import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RangeBasedStrategy implements DispatchStrategy {

    private static final Logger log = LoggerFactory.getLogger(RangeBasedStrategy.class);

    private final int totalFloors;
    private final int elevatorCount;

    public RangeBasedStrategy(int totalFloors, int elevatorCount) {
        this.totalFloors = totalFloors;
        this.elevatorCount = elevatorCount;
    }

    @Override
    public Elevator assignElevator(List<Elevator> elevators, FloorRequest request) {

        int floorsAllocatedToEach = totalFloors / elevatorCount;
        int requestSourceFloor = request.getSourceFloor();

        for (Elevator elevator : elevators) {

            int index = elevator.getId() - 1; // IDs assumed 1-based
            int startFloor = index * floorsAllocatedToEach;
            int endFloor = (index == elevators.size() - 1)
                    ? totalFloors
                    : startFloor + floorsAllocatedToEach - 1; // -1 to avoid overlap

            if (requestSourceFloor >= startFloor && requestSourceFloor <= endFloor) {

                log.info("range_strategy=match elevatorId={} range=[{} - {}] src={}",
                        elevator.getId(), startFloor, endFloor, requestSourceFloor);

                return elevator;
            }
        }

        // No range matched → fallback
        log.info("range_strategy=fallback_to_nearest src={}", requestSourceFloor);
        return new NearestElevatorStrategy().assignElevator(elevators, request);
    }
}
