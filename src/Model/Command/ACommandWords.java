package Model.Command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Fabien PHAM
 */
public abstract class ACommandWords
{
    /**
     * a Map containing all valid command words
     */
    protected final Map<String, ICommand> commands;
    
    protected ACommandWords() { this.commands = new HashMap<>(); }
    
    /**
     * Check if parameter is an existing command
     * @param commandWord
     * @return
     */
    public boolean isCommand(String commandWord)
    {
        return this.commands.containsKey(commandWord);
    }
    
    /**
     *
     * Get the appropriate command depending on parameter
     * @param commandWord
     * @return
     */
    public ICommand getAppropriateCommand(String commandWord)
    {
        return this.commands.get(commandWord);
    }
    
    /**
     * Get all the names of existing commands
     * @return
     */
    public List<String> getAllStringCommands()
    {
        return new ArrayList<>(this.commands.keySet());
    }
}
