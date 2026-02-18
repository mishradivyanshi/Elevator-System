package com.example.elevator.concurrent;

import com.example.elevator.controller.ElevatorController;
import com.example.elevator.model.FloorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RequestProducer implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(RequestProducer.class);

    private final ElevatorController controller;
    private final int totalFloors;
    private final Random random = new Random();
    private volatile boolean running = true;

    public RequestProducer(ElevatorController controller, int totalFloors) {
        this.controller = controller;
        this.totalFloors = totalFloors;
    }

    @Override
    public void run() {
        log.info("producer=started");

        try {
            while (running) {
                int source = random.nextInt(totalFloors);
                int dest;
                do {
                    dest = random.nextInt(totalFloors);
                } while (dest == source);
                boolean service = random.nextInt(10) == 0; // approx 10%
                controller.submitRequest(new FloorRequest(source, dest, service));
                log.info("producer=request src={} dst={} service={}", source, dest, service);
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.warn("producer=interrupted");
        }

        log.info("producer=stopped");
    }

    public void stop() {
        running = false;
        log.info("producer=stop_requested");
    }
}
