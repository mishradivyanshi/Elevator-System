package com.example.elevator.strategy;

public class StrategyFactory {
    public static DispatchStrategy getStrategy(String name, int totalFloors, int elevatorCount) {
        return switch (name.toUpperCase()) {
            case "RANGE" -> new RangeBasedStrategy(totalFloors, elevatorCount);
            case "SERVICE" -> new ServiceElevatorStrategy(totalFloors, elevatorCount);
            case "NEAREST" -> new NearestElevatorStrategy();
            default -> new NearestElevatorStrategy();
        };
    }
}
