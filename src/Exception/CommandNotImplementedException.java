package Exception;

/**
 * Custom exception for commands of Fotoshop application
 * @author Fabien PHAM
 */
public class CommandNotImplementedException extends Exception
{

    /**
     * Initialize exception with the sentence adapted
     * @param commandWord
     */
    public CommandNotImplementedException(String commandWord)
    {
        super("The command \"" + commandWord + "\" hasn't be implemented");
    }
}
