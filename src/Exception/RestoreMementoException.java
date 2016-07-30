package Exception;

/**
 * Custom exception for pattern memento of Fotoshop application
 * @author Fabien PHAM
 */
public class RestoreMementoException extends Exception
{

    /**
     * Initialize exception with the sentence adapted
     */
    public RestoreMementoException()
    {
        super("The restoration of a memento has failed");
    }
}
