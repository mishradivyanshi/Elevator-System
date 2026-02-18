package com.example.elevator.concurrent;

import com.example.elevator.model.Elevator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ElevatorTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ElevatorTask.class);

    private final Elevator elevator;
    private volatile boolean running = true;

    public ElevatorTask(Elevator elevator) {
        this.elevator = elevator;
    }

    @Override
    public void run() {
        log.info("elevatorTask=started | id={}", elevator.getId());
        try {
            while (running) {
                elevator.processNextRequest();
                Thread.sleep(200); // simulate idle polling delay
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("elevatorTask=interrupted | id={}", elevator.getId());
        }
        log.info("elevatorTask=stopped | id={}", elevator.getId());
    }

    public void stop() {
        running = false;
        log.info("elevatorTask=stop_requested | id={}", elevator.getId());
    }
}

