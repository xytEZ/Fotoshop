package Exception;

/**
 * Custom exception for invalid input
 * @author pham_d
 */
public class InvalidInputException extends Exception
{

    /**
     * Initialize exception with the sentence adapted
     */
    public InvalidInputException()
    {
        super("User input is non-existent");
    }
    
    /**
     * Initialize exception with string parameter
     * @param s
     */
    public InvalidInputException(String s) { super(s); }
    
}
