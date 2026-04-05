package dev.ssims.fizzbuzz;

/**
 * A classic FizzBuzz implementation.
 * <p>
 * Iterates through numbers and prints:
 * <ul>
 *   <li><strong>FizzBuzz</strong> — if the number is a multiple of both 3 and 5</li>
 *   <li><strong>Fizz</strong> — if the number is a multiple of 3 only</li>
 *   <li><strong>Buzz</strong> — if the number is a multiple of 5 only</li>
 *   <li>The number itself — otherwise</li>
 * </ul>
 */
public class Main {

    /**
     * Entry point. Runs FizzBuzz for numbers 2 through 100,000.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {

        for (int x = 2; x <= 100000; x++) {
            if (multipleOf(3, x) && multipleOf(5, x)) {
                System.out.println(x + " FizzBuzz");

            }else if (multipleOf(3, x)) {
                System.out.println(x + " Fizz");

            }else if(multipleOf(5,x)){
                System.out.println(x + " Buzz");

            }else {
                System.out.println(x);
            }
        }
    }

    /**
     * Checks whether a given number is a multiple of another.
     *
     * @param multiple the divisor to check against
     * @param check    the number to test
     * @return {@code true} if {@code check} is evenly divisible by {@code multiple}
     */
    public static boolean multipleOf(int multiple, int check) {
        return (check % multiple) == 0;

    }
}
