package com.example.elevator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Elevator {

    private static final Logger log = LoggerFactory.getLogger(Elevator.class);

    private int id;
    private int currentFloor = 0;
    private boolean serviceElevator;
    private ElevatorStatus status = ElevatorStatus.IDLE;
    private Direction direction = Direction.IDLE;

    private final BlockingQueue<FloorRequest> requestQueue = new LinkedBlockingQueue<>();
    private volatile boolean running = true;

    public Elevator(int id, int currentFloor, boolean isServiceElevator) {
        this.id = id;
        this.currentFloor = currentFloor;
        this.serviceElevator = isServiceElevator;
    }

    public boolean isRunning() { return running; }
    public int getId() { return id; }
    public int getCurrentFloor() { return currentFloor; }
    public ElevatorStatus getStatus() { return status; }
    public Direction getDirection() { return direction; }
    public boolean isServiceElevator() { return serviceElevator; }
    public int getPendingRequestCount() { return requestQueue.size(); }

    public void addRequest(FloorRequest request) {
        requestQueue.add(request);

        log.info("elevator=request_added id={} src={} dst={} service={} pending={}",
                id, request.getSourceFloor(), request.getDestinationFloor(),
                request.isServiceRequest(), requestQueue.size());
    }

    public void processNextRequest() {
        FloorRequest next = requestQueue.poll();
        if (next == null) return;

        log.info("elevator=processing id={} src={} dst={}",
                id, next.getSourceFloor(), next.getDestinationFloor());

        // Move to pickup
        moveTo(next.getSourceFloor());
        log.info("elevator=picked_up id={} floor={}", id, currentFloor);

        // Move to drop-off
        moveTo(next.getDestinationFloor());
        log.info("elevator=dropped_off id={} floor={}", id, currentFloor);
    }

    public void moveTo(int targetFloor) {

        // Already at destination
        if (currentFloor == targetFloor) {
            log.info("elevator=already_at id={} floor={}", id, targetFloor);
            return;
        }

        direction = targetFloor > currentFloor ? Direction.UP : Direction.DOWN;
        status = ElevatorStatus.MOVING;

        log.info("elevator=move_start id={} from={} to={} dir={}",
                id, currentFloor, targetFloor, direction);

        while (currentFloor != targetFloor) {

            currentFloor += (direction == Direction.UP) ? 1 : -1;
            log.info("elevator=passing id={} floor={}", id, currentFloor);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.warn("elevator=interrupted id={} current={} target={}",
                        id, currentFloor, targetFloor);
                return;
            }
        }

        status = ElevatorStatus.IDLE;
        direction = Direction.IDLE;

        log.info("elevator=arrived id={} floor={} pending={}",
                id, currentFloor, requestQueue.size());
    }

    public void stopElevator() {
        running = false;
        log.info("elevator=stop_requested id={}", id);
    }

    @Override
    public String toString() {
        return "Elevator{id=" + id +
                ", currentFloor=" + currentFloor +
                ", service=" + serviceElevator +
                ", pending=" + getPendingRequestCount() + "}";
    }
}
