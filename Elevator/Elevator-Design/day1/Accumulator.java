//Author : Maitreyee Joshi -- Exercise : 4. Loops : Accumulator

/* Sample run
Enter integers to sum up. Enter 0 to stop.
Enter a number:
3
Running Total: 3
Enter a number:
23
Running Total: 26
Enter a number:
-10
Running Total: 16
Enter a number:
3.44
Invalid input.Enter a whole number.
Enter a number:
344
Running Total: 360
Enter a number:
100
Running Total: 460
Enter a number:
0
Final Total: 460
*/

package day1;

import java.util.Scanner;

public class Accumulator {
    void main(String[] args) {
        //Scanner for user input
        Scanner sc = new Scanner(System.in);

        int accumulatedTotal = 0;

        int number; // Hold current user input

        System.out.println("Enter integers to sum up. Enter 0 to stop.");

        do {
            System.out.println("Enter a number: ");

            //Validate whether it is integer
            if (sc.hasNextInt()) {
                number = sc.nextInt();

                //Check for stopping condition
                if (number != 0) {
                    accumulatedTotal += number;
                    System.out.println("Running Total: " + accumulatedTotal);
                }
            } else {
                System.out.println("Invalid input.Enter a whole number.");
                sc.next();
                number = -1; // Set non-zero value to keep loop going on
            }
        } while (number != 0);

        System.out.println("Final Total: " + accumulatedTotal);

        sc.close();
    }
}
