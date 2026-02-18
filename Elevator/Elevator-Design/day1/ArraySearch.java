//Author : Maitreyee Joshi -- Exercise : 5. Array and Strings : Array Slot Search

/*Sample run
Array:
30
42
15
39
37
38
34
38
47
20

Value to find:
38
38 is in slot 8.
 */

package day1;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class ArraySearch {
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

        //Search the array
        int lastSlot = -1; //Indicates value not found

        for (int i = 0; i < array.length; i++) {
            if (array[i] == targetValue) {
                lastSlot = i + 1; // Slot at index 0 is 1 (1-based slot)
            }
        }

        //Display result
        if (lastSlot != -1) {
            System.out.println(targetValue + " is in slot " + lastSlot + ".");
        } else {
            System.out.println(targetValue + " is not present in the array.");
        }
    }
}
