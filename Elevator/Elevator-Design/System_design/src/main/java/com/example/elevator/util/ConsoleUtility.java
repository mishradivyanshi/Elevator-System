package com.example.elevator.util;

import com.example.elevator.controller.ElevatorController;
import com.example.elevator.model.FloorRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class ConsoleUtility {

    private static final Logger log = LoggerFactory.getLogger(ConsoleUtility.class);

    private final ElevatorController controller;
    private final Scanner inputScanner = new Scanner(System.in);

    public ConsoleUtility(ElevatorController controller) {
        this.controller = controller;
    }

    public void startConsole() {

        System.out.println("\n=== Elevator Control Console ===");
        System.out.println("Commands Available:");
        System.out.println("  request <src> <dst> <normal|service>");
        System.out.println("  status");
        System.out.println("  exit\n");

        while (true) {
            System.out.print("> ");
            String command = inputScanner.nextLine().trim();

            if (command.isEmpty()) continue;

            if (command.equalsIgnoreCase("exit")) {
                log.info("console=shutdown_requested");
                controller.shutdown();
                System.out.println("System shutting down...");
                break;
            }

            if (command.equalsIgnoreCase("status")) {
                controller.getElevatorStatus().forEach(System.out::println);
                continue;
            }

            if (command.startsWith("request") || command.matches("\\d+\\s+\\d+\\s+\\w+")) {
                handleRequest(command);
                continue;
            }

            System.out.println("Unknown command. Use:  request | status | exit");
        }
    }

    private void handleRequest(String command) {
        String[] tokens = command.split("\\s+");

        int indexShift = tokens[0].equalsIgnoreCase("request") ? 1 : 0;
        if (tokens.length < indexShift + 3) {
            System.out.println("Invalid format. Example:  request 2 10 normal");
            return;
        }

        try {
            int sourceFloor = Integer.parseInt(tokens[indexShift]);
            int destinationFloor = Integer.parseInt(tokens[indexShift + 1]);
            boolean serviceMode = tokens[indexShift + 2].toLowerCase().startsWith("s");

            FloorRequest request = new FloorRequest(sourceFloor, destinationFloor, serviceMode);
            boolean assigned = controller.submitRequest(request);

            if (assigned) {
                System.out.printf("✔ Request %d → %d (%s)%n", sourceFloor, destinationFloor,
                        serviceMode ? "service" : "normal");
            } else {
                System.out.println("Request could not be assigned.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Floors must be integers.");
        }
    }
}
