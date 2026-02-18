package com.example.elevator.controller;

import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import com.example.elevator.strategy.DispatchStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class RequestDispatcher {

    private static final Logger log = LoggerFactory.getLogger(RequestDispatcher.class);

    private static RequestDispatcher instance;   // singleton
    private final List<Elevator> elevators;
    private final DispatchStrategy strategy;

    private RequestDispatcher(List<Elevator> elevators, DispatchStrategy strategy) {
        this.elevators = elevators;
        this.strategy = strategy;
    }

    public static synchronized void init(List<Elevator> elevators, DispatchStrategy strategy) {
        if (instance == null) {
            instance = new RequestDispatcher(elevators, strategy);
        } else {
            throw new IllegalStateException("RequestDispatcher already initialized");
        }
    }

    public static RequestDispatcher getInstance() {
        if (instance == null) {
            throw new IllegalStateException("RequestDispatcher not initialized");
        }
        return instance;
    }

    public Elevator dispatchRequest(FloorRequest request) {
        Elevator selected = strategy.assignElevator(elevators, request);

        if (selected != null) {
            log.info("dispatcher=assigned src={} dst={} elevator={}",
                    request.getSourceFloor(), request.getDestinationFloor(), selected.getId());

            selected.addRequest(request);
        } else {
            log.warn("dispatcher=no_elevator_available src={} dst={}",
                    request.getSourceFloor(), request.getDestinationFloor());
        }

        return selected;
    }

    public List<Elevator> getElevators() {
        return elevators;
    }
}
