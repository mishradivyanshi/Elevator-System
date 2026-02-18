package com.example.elevator.controller;

import com.example.elevator.concurrent.ElevatorTask;
import com.example.elevator.model.Elevator;
import com.example.elevator.model.FloorRequest;
import com.example.elevator.strategy.DispatchStrategy;
import com.example.elevator.strategy.StrategyFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ElevatorController {

    private static final Logger log = LoggerFactory.getLogger(ElevatorController.class);

    private final int totalFloors;
    private final int elevatorCount;
    private final List<Elevator> elevators = new ArrayList<>();
    private final RequestDispatcher dispatcher;
    private final ExecutorService executor; // runs elevators concurrently

    public ElevatorController(int totalFloors, int elevatorCount, DispatchStrategy strategy) {
        this.totalFloors = totalFloors;
        this.elevatorCount = elevatorCount;
        this.executor = Executors.newFixedThreadPool(elevatorCount);

        // Create elevators (last one is service elevator)
        for (int i = 1; i <= elevatorCount; i++) {
            boolean isService = (i == elevatorCount);
            Elevator elevator = new Elevator(i, 0, isService);
            elevators.add(elevator);
        }

        // Initialize dispatcher with elevators & strategy
        RequestDispatcher.init(elevators, strategy);
        this.dispatcher = RequestDispatcher.getInstance();

        // Run each elevator concurrently
        for (Elevator elevator : elevators) {
            executor.submit(new ElevatorTask(elevator));
            log.info("controller=started elevatorId={} service={}",
                    elevator.getId(), elevator.isServiceElevator());
        }

        log.info("controller=initialized totalFloors={} elevators={} strategy={}",
                totalFloors, elevatorCount, strategy.getClass().getSimpleName());
    }

    public boolean submitRequest(FloorRequest request) {
        log.info("controller=submitting_request src={} dst={} service={}",
                request.getSourceFloor(), request.getDestinationFloor(), request.isServiceRequest());

        Elevator assigned = dispatcher.dispatchRequest(request);

        if (assigned == null) {
            log.warn("controller=request_not_assigned src={} dst={}",
                    request.getSourceFloor(), request.getDestinationFloor());
            return false;
        }

        log.info("controller=request_assigned elevatorId={}", assigned.getId());
        return true;
    }

    public List<String> getElevatorStatus() {
        List<String> snapshot = new ArrayList<>();

        for (Elevator elevator : elevators) {
            snapshot.add(String.format(
                    "Elevator[id=%d, floor=%d, service=%b, pending=%d]",
                    elevator.getId(),
                    elevator.getCurrentFloor(),
                    elevator.isServiceElevator(),
                    elevator.getPendingRequestCount()
            ));
        }
        return snapshot;
    }

    public void shutdown() {
        log.info("controller=shutdown_requested");

        for (Elevator elevator : elevators) {
            elevator.stopElevator();
        }

        executor.shutdownNow();
        log.info("controller=all_elevators_stopped");
    }

    public List<Elevator> getElevators() {
        return elevators;
    }

    public RequestDispatcher getDispatcher() {
        return dispatcher;
    }
}
