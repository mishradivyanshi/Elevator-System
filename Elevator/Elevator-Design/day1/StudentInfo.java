//Author: Maitreyee Joshi -- Exercise: 2. Variable and Data Types

/*Sample run
Using Sys.out: Alice (21 yrs) scored 78.5% - Pass
Using String.format: Alice (21 yrs) scored 78.5% - Pass
 */

package day1;

public class StudentInfo {
    void main(String[] args) {
        //Declare variables with appropriate data type
        String name = "Alice";

        int age = 21;

        double percentage = 78.5;

        String result = "Pass";

        //Using System.out.println()
        System.out.println("Using Sys.out: " + name + " (" + age + " yrs) scored " + percentage + "% - " + result);

        //Using String.format()
        String formatString = "%s (%d yrs) scored %.1f%% - %s";
        String output = String.format(formatString, name, age, percentage, result);
        System.out.println("Using String.format: " + output);
    }
}
