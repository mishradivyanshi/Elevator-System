package com.example.elevator.strategy;

import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class NearestElevatorStrategy implements DispatchStrategy {

    private static final Logger log = LoggerFactory.getLogger(NearestElevatorStrategy.class);

    @Override
    public Elevator assignElevator(List<Elevator> elevators, FloorRequest request) {
        Elevator nearest = elevators.stream()
                .min((e1, e2) -> {
                    int d1 = Math.abs(e1.getCurrentFloor() - request.getSourceFloor());
                    int d2 = Math.abs(e2.getCurrentFloor() - request.getSourceFloor());
                    return Integer.compare(d1, d2);
                })
                .orElse(null);

        log.info("strategy=nearest | chosenElevator={} | sourceFloor={} | totalElevators={}",
                nearest != null ? nearest.getId() : -1,
                request.getSourceFloor(),
                elevators.size());

        return nearest;

    }
}

