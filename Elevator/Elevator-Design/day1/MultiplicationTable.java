//Author : Maitreyee Joshi -- Exercise : 4. Loops : Multiplication table

/* Sample Runs
Sample 1
Enter a number N (1-12) :
7
Multiplication Table for 7
1 * 7 = 7
2 * 7 = 14
3 * 7 = 21
4 * 7 = 28
5 * 7 = 35
6 * 7 = 42
7 * 7 = 49
8 * 7 = 56
9 * 7 = 63
10 * 7 = 70
11 * 7 = 77
12 * 7 = 84

Sample 2
Enter a number N (1-12) :
5
Multiplication Table for 5
1 * 5 = 5
2 * 5 = 10
3 * 5 = 15
4 * 5 = 20
5 * 5 = 25
6 * 5 = 30
7 * 5 = 35
8 * 5 = 40
9 * 5 = 45
10 * 5 = 50
11 * 5 = 55
12 * 5 = 60

Sample 3
Enter a number N (1-12) :
12
Multiplication Table for 12
1 * 12 = 12
2 * 12 = 24
3 * 12 = 36
4 * 12 = 48
5 * 12 = 60
6 * 12 = 72
7 * 12 = 84
8 * 12 = 96
9 * 12 = 108
10 * 12 = 120
11 * 12 = 132
12 * 12 = 144

Sample 4
Enter a number N (1-12) :
-6
Invalid input: N must be between 1 and 12

Sample 5
Enter a number N (1-12) :
6.7
Invalid input. Enter an integer

* */

package day1;

import java.util.Scanner;

public class MultiplicationTable {
    void main(String[] args) {

        //Scanner to read integer n between 1 and 12
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a number N (1-12) : ");

        if (!sc.hasNextInt()) {
            System.out.println("Invalid input. Enter an integer");
            sc.close();
            return;
        }

        int N = sc.nextInt();
        sc.close();

        //Validate user input
        if (N < 1 || N > 12) {
            System.out.println("Invalid input: N must be between 1 and 12");
            return;
        }
        System.out.println("Multiplication Table for " + N);

        //For loop to iterate from 1 to 12
        for (int i = 1; i <= 12; i++) {
            int result = N * i;
            System.out.println(i + " * " + N + " = " + result);
        }
    }
}
