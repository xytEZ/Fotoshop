package Model.Command;
        
import Exception.CommandNotImplementedException;

/**
 * Class based on the factory pattern for centralize the creation of 
 * command class
 * @author Fabien PHAM
 */
public final class CommandFactory
{

    /**
     * Get the dependency about the right command class using the pattern
     * "Injection of Dependency"
     * @param commandWords
     * @param commandWord
     * @return
     * @throws CommandNotImplementedException
     */
    public ICommand getDependency(ACommandWords commandWords, String commandWord)
            throws CommandNotImplementedException
    {
        if (!commandWords.isCommand(commandWord))
            throw new CommandNotImplementedException(commandWord);
        return commandWords.getAppropriateCommand(commandWord);
    }
}
