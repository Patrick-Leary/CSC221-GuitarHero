
/**Implements a guitar class named GuitarString
  * 
  * @author Mika Latifov
  * @author Patrick Leary
*/



import java.util.*;

public class GuitarString {
    private double DECAY_FACTOR = 0.996;
    private Queue<Double> ringBuffer;
    private int n;
    private int count = 0;


    /**
      * Costructs a guitar string of a given frequency
      * Condtructs a linked list with the name ringBuffer
      * if the frequency is below 0 or the ring buffer size is below 2, it will throw an illegal argument exception
      * 
      * @param frequency
    */
    public GuitarString(double frequency) {
        ringBuffer = new LinkedList<Double>();
        n = (int) Math.round((double)StdAudio.SAMPLE_RATE / frequency); // sets the desired capacity of the ring buffer and rounds to the nearest int
        for (double i = 0; i < n; i++) {
            ringBuffer.add(0.0);
        }

        if ((frequency <= 0) || ringBuffer.size() < 2) {
            throw new IllegalArgumentException();
        }
    }

    /**
      * Adds the given elements to the ringBuffer
      * @param init
    */
    public GuitarString(double[] init) {
        ringBuffer = new LinkedList<Double>();
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }

        for (double elements : init) {
            ringBuffer.add(elements);
        }
    }

    /**
      * Replaces values within the linked list to random values between -0.5 and 0.5
      * 
    */
    public void pluck() {
        for (int i = 0; i < n - 1; i++) {
            ringBuffer.remove();
            ringBuffer.add(Math.random() - 0.5);
        }
    }

    /**
      * runs the Karplus-Strong update once and adds one to the variable couont afterwards
    */
    public void tic() {
        double firstNumber = ringBuffer.remove();
        double secondNumber = ringBuffer.peek();
        double newNumber = DECAY_FACTOR * ((firstNumber + secondNumber) / 2);
        ringBuffer.add(newNumber);
        count++;
    }

    /**
      * returns the front value of the ring buffer
      * @return
    */
    public double sample() {
        return ringBuffer.peek();
    }

    /**
      * returns the value of count, which is how many times the tic method has been called
      * @return
    */
    public int time() {
        return count;
    }

}