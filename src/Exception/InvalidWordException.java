package Exception;

/**
 *
 * @author Fabien PHAM
 */
public class InvalidWordException extends Exception
{
    public InvalidWordException()
    {
        super("User word is non-existent");
    }
    
    public InvalidWordException(String s) { super(s); }
}
