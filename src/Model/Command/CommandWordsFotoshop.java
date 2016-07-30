package Model.Command;

import Model.Command.Type.*;

/**
 * Class containing a key-value of the name of command and his instance
 * @author Fabien PHAM
 */

public final class CommandWordsFotoshop extends ACommandWords
{
    /**
     * Map containing all valid command words
     */
    public CommandWordsFotoshop()
    {
        super();
        this.commands.put("help", new HelpCommand());
        this.commands.put("open", new OpenCommand());
        this.commands.put("save", new SaveCommand());
        this.commands.put("mono", new MonoCommand());
        this.commands.put("rot90", new Rot90Command());
        this.commands.put("look", new LookCommand());
        this.commands.put("script", new ScriptCommand());
        this.commands.put("quit", new QuitCommand());
        this.commands.put("undo", new UndoCommand());
        this.commands.put("get", new GetCommand());
        this.commands.put("put", new PutCommand());
    }
}
