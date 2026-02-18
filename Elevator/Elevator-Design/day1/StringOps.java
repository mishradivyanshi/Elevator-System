//Author : Maitreyee Joshi -- Exercise : 5. Arrays and Strings : Sting Operations

/*Sample run
Uppercase : ALICE SMITH
Lowercase: alice smith
Reversed String: htimS ecilA
Vowel Count: 4
Character Frequency:
 A: 1
 S: 1
 c: 1
 e: 1
 h: 1
 i: 2
 l: 1
 m: 1
 t: 1
 */

package day1;

public class StringOps {
    void main(String[] args) {
        String name = "Alice Smith";

        //Uppercase and Lowercase
        System.out.println("Uppercase : " + name.toUpperCase());
        System.out.println("Lowercase: " + name.toLowerCase());

        //Reverse String
        String reversed = "";
        for (int i = name.length() - 1; i >= 0; i--) {
            reversed += name.charAt(i);
        }
        System.out.println("Reversed String: " + reversed);

        //Vowel Count
        int vowelcount = 0;
        String vowels = "aeiouAEIOU";

        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                vowelcount++;
            }
        }
        System.out.println("Vowel Count: " + vowelcount);

        //Character Frequency
        int[] charFreq = new int[256];

        for (char ch : name.toCharArray()) {
            charFreq[ch]++;
        }
        System.out.println("Character Frequency: ");
        for (int i = 0; i < charFreq.length; i++) {
            if (charFreq[i] > 0 && i != ' ') {
                System.out.println(" " + (char) i + ": " + charFreq[i]);
            }
        }
    }
}
