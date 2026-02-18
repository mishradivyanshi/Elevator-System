//Author : Maitreyee Joshi -- Exercise : 3. Operators and Conditionals

package day1;

import java.util.Scanner;

public class GradeApp {
    void main(String[] args) {
        //Use scanner to raed user input
        Scanner sc = new Scanner(System.in);

        //Get user input
        System.out.println("Enter marks (0-100): ");
        int marks = sc.nextInt();

        //Validate user input: marks beyond 0 and 100
        if (marks < 0 || marks > 100) {
            System.out.println("Invalid input: marks must be between 0 and 100");
            return;
        }

        //Grade mapping
        String grade;

        if (marks >= 85) {
            grade = "A";
        } else if (marks >= 70) {
            grade = "B";
        } else if (marks >= 55) {
            grade = "C";
        } else if (marks >= 40) {
            grade = "D";
        } else {
            grade = "F";
        }
        sc.close();

        //Print the result
        System.out.println("Marks: " + marks + ", Grade: " + grade);
    }
}
