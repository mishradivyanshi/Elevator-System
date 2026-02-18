//Author : Maitreyee Joshi -- Exercise : 5. Array and Strings : Array Multiple Slot Search

/*Sample run
Array:
13
41
36
35
22
19
26
26
18
48

Value to find:
26
26 is in slots 7, 8.
 */

package day1;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ArraySearchMultipleSlots {
    void main(String[] args) {
        //Create array to hold 10 integers
        int[] array = new int[10];
        Random random = new Random();

        //Fill the array with numbers from 1 to 50
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(50) + 1;
        }
        //Display the array
        System.out.println("Array: ");
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
        System.out.println();

        //Prompt for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Value to find: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Enter an integer");
            sc.close();
            return;
        }

        int targetValue = sc.nextInt();
        sc.close();

        //Use ArrayList to store ALL the slot numbers
        List<Integer> foundSlots = new ArrayList<>();

        //Search the array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == targetValue) {
                foundSlots.add(i + 1); //Add slot number to list
            }
        }

        //Display Result
        if (foundSlots.isEmpty()) {
            System.out.println(targetValue + " is not present in the array.");
        } else if (foundSlots.size() == 1) {
            System.out.println(targetValue + " is in slot " + (foundSlots.get(0)) + ".");
        } else {
            String slotstring = foundSlots.toString().replace("[", "").replace("]", "");
            System.out.println(targetValue + " is in slots " + slotstring + ".");
        }

    }
}
