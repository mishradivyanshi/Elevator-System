/*
Author: Maitreyee Joshi -- Exercise: 6.Java Random Class

Sample run
Random Numbers:
Random Number: 154
Random Number: 177
Random Number: 139
Random Number: 182
Random Number: 129
Random Number: 184
Random Number: 164
Random Number: 199
Random Number: 179
Random Number: 102
 */

package day1;

import java.util.Random;

public class RandomNumbers {
    void main(String[] args) {
        //Random class instance
        Random random = new Random();

        //Range parameters including 100 ad 200
        int min = 100;
        int max = 200;
        int range = max - min + 1; //To get [min, max] inclusive range
        //System.out.println(range);

        //Random numbers generation
        System.out.println("Random Numbers: ");

        for (int i = 0; i < 10; i++) {
            int randomNumber = random.nextInt(range) + min;
            System.out.println("Random Number: " + randomNumber);
        }
    }
}
