package com.example.elevator.strategy;

import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import com.sun.net.httpserver.Request;

import java.util.List;

@FunctionalInterface
public interface DispatchStrategy {
    Elevator assignElevator(List<Elevator> elevators, FloorRequest request);

}
