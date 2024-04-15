/**
 * Creates a guitar with 37 strings and corresponding pitches for each string
 * 
 * @author Patrick Leary
 * @author Mika Latifov
 */



public class Guitar37 implements Guitar {

    private int NUMBER_OF_STRINGS = 37;
    private GuitarString[] frequency;
    private double INITIAL_FRQUENCY = 440.0;
    public static final String KEYBOARD = 
        "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";


    /**
     * Initializes an array with frequencies in increasing order
     */
    public Guitar37() {
        frequency = new GuitarString[NUMBER_OF_STRINGS];
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            frequency[i] = new GuitarString(INITIAL_FRQUENCY * (Math.pow(2, (i-24.0)/12.0)));
        }
    }

    /**
     *Checks to see if the given string is one of the 37 strings
     *
     * @param string
     */
    public boolean hasString(char string) {
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            if (string == KEYBOARD.charAt(i)) {
                return true;
            }
        }
        throw new IllegalArgumentException();
    }


    /**
     * Plays the pitch of the given string
     * 
     * if the given note is not on the keyboard, then throws an illegal argument exception
     * 
     * @param string
     */
    public void pluck(char string) {
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            if (string == KEYBOARD.charAt(i)) {
                frequency[i].pluck();
            }
        }
        if (hasString(string) != true) {
            throw new IllegalArgumentException();
        } 
    }

    /**
     * Plays the song from the given sample
     */
    public void play() {
        double sample = 0.0;
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            sample += frequency[i].sample();
        }
        StdAudio.play(sample);
    }
    

    /**
     * Applies the method tic for each of the notes
     */
    public void tic() {
        for (int i = 0; i < NUMBER_OF_STRINGS; i++) {
            frequency[i].tic();
        }
    }
}