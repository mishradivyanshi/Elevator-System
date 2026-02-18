//Author : Maitreyee Joshi -- Exercise : 4.Loops : Factorial

/* Sample Runs

Sample 1
Enter a number: 
4
Iterative Factorial of 4 is 24
Recursive Factorial of 4 is 24


Sample 2
Enter a number: 
25
Iterative Factorial of 25 is 7034535277573963776
Recursive Factorial of 25 is 7034535277573963776

Sample 3
Enter a number: 
7
Iterative Factorial of 7 is 5040
Recursive Factorial of 7 is 5040

Sample 4
Enter a number:
-5
Invalid input: N must be positive integer or 0

Sample 5
Enter a number:
5.5
Invalid input. Enter an integer
* */


package day1;

import java.util.Scanner;

public class Factorial 

    public static long factorialRecursive(int N) {
        if(N == 0) {
            return 1;
        }
        return N * factorialRecursive(N - 1);
    }
    
    void main(String[] args) {
        //Scanner for user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number: ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Enter an integer");
            return;
        }

        int N = sc.nextInt();
        sc.close();

        //Validate input
        if (N < 0) {
            System.out.println("Invalid input: N must be positive integer or 0");
            return;
        }

        //calculate factorial
        long factorial = 1;

        for (int i = 1; i <= N; i++) {
            factorial *= i;
        }
        System.out.println("Iterative Factorial of " + N + " is " + factorial);
        System.out.println("Recursive Factorial of " + N + " is "+ factorialRecursive(N));
    }
}
