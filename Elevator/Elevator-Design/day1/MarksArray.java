//Author : Maitreyee Joshi -- Exercise : 5. Array and Strings : Average, Highest, Lowest

/* Sample run
All Marks: 78, 91, 45, 66, 55
Average Marks (double): 67.0
Highest Marks: 91
Lowest Marks: 45
*/

package day1;

public class MarksArray {
    void main(String[] args) {
        //Hardcode int[] marks array
        int[] marks = {78, 91, 45, 66, 55};

        int sum = 0;

        int highest = marks[0];
        int lowest = marks[0];

        for (int mark : marks) {
            //Compute Sum
            sum += mark;

            //Find highest
            if (mark > highest) {
                highest = mark;
            }

            //Find Lowest
            if (mark < lowest) {
                lowest = mark;
            }
        }

        //Compute average(double)
        double average = (double) sum / marks.length;

        //Labeled outputs
        System.out.println("All Marks: 78, 91, 45, 66, 55");
        System.out.println("Average Marks (double): " + average);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);


    }
}
