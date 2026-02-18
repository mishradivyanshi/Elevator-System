package com.example.elevator;

import com.example.elevator.concurrent.RequestProducer;
import com.example.elevator.controller.ElevatorController;
import com.example.elevator.strategy.DispatchStrategy;
import com.example.elevator.strategy.StrategyFactory;
import com.example.elevator.util.ConfigLoader;
import com.example.elevator.util.ConsoleUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int totalFloors = ConfigLoader.getInt("totalFloors", 50);
        int elevatorCount = ConfigLoader.getInt("elevatorCount", 15);
        String strategyName = ConfigLoader.getString("strategy", "RANGE");

        DispatchStrategy strategy = StrategyFactory.getStrategy(strategyName, totalFloors, elevatorCount);

        ElevatorController controller = new ElevatorController(totalFloors, elevatorCount, strategy);

        //start a request producer to auto-generate requests
         RequestProducer producer = new RequestProducer(controller, totalFloors);
         Thread p = new Thread(producer, "request-producer");
         p.start();

         //stop producer after 30 seconds
        try {
            Thread.sleep(30000);
            producer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //manual input
//        ConsoleUtility console = new ConsoleUtility(controller);
//        console.runInteractive();

        log.info("Main exiting");
    }
}
