package examples;

/**
 * A utility class to provide help to other classes.
 *
 * @author (Per Lauvås)
 * @version (0.2)
 */
public class Util {

    /**
     * Constructor for objects of class Util
     */
    public Util() {
    }

    /**
     * Does something with incoming numbers.
     *
     * @param numbers an array of ints
     * @return an int, but what does it convey?
     */
    public int handleNumbers(int[] numbers) {
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public int avg(int[] numbers) {
        int sum = handleNumbers(numbers);
        return sum / numbers.length;
    }
}
