package Model;

import Model.Command.ACommandWords;
import Model.Command.CommandFactory;
import Model.Command.CommandExecutor;

/**
 * Abstraction class for all futures implementation of editor
 * @author Fabien PHAM
 */
public abstract class AEditor extends AModel
{
    /**
     * Abstract class for all valid command words
     */
    protected ACommandWords     commandWords;
    
    /**
     * Object for manage all processing command
     */
    protected CommandExecutor   commandExecutor;

    /**
     * Object for create the right command and centralize the creation
     */
    protected CommandFactory    commandFactory;

    /**
     * Object for analyze the input
     */
    protected Parser            parser;
    
    /**
     * Initialize all attributes which all bounded to editor
     * @param commandWords
     */
    protected AEditor(ACommandWords commandWords)
    {
        super();
        this.commandWords = commandWords;
        this.commandExecutor = new CommandExecutor();
        this.commandFactory = new CommandFactory();
        this.parser = new Parser();
    }
    
    /**
     * Get the class containing all valid command words
     * @return 
     */
    public ACommandWords getCommandWords() { return this.commandWords; }
    
    /**
     * Get the class managing the execution of command
     * @return 
     */
    public CommandExecutor getCommandExecutor() { return this.commandExecutor; }
   
    /**
     * Get the factory command centralizing the creation of command
     * @return 
     */
    public CommandFactory getCommandFactory() { return this.commandFactory; }
    
    /**
     * Edit the right action which is bounded with the input
     * @param input 
     */
    public abstract void edit(String input);
}