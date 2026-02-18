package com.example.elevator.model;

public class FloorRequest {

    public int sourceFloor;
    public int destinationFloor;
    public boolean serviceRequest;

    public FloorRequest(int sourceFloor, int destinationFloor, boolean serviceRequest) {
        this.sourceFloor = sourceFloor;
        this.destinationFloor = destinationFloor;
        this.serviceRequest = serviceRequest;

    }

    public int getSourceFloor() {
        return sourceFloor;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    public boolean isServiceRequest() {
        return serviceRequest;
    }

    @Override
    public String toString() {
        return "Request: " + sourceFloor + " -> " + destinationFloor +
                (serviceRequest ? " (Service)" : " (Normal)");
    }
}
