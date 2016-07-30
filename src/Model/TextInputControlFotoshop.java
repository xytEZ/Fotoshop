package Model;

import Exception.CommandNotImplementedException;
import Exception.InvalidInputException;
import Exception.InvalidWordException;
import Model.Command.RequestCommand;
import Model.Manager.InternationalizationManager;

/**
 * Check input user and prepare execution of command in application
 * @author Fabien PHAM
 */
public final class TextInputControlFotoshop extends ATextInputControlEditor
{
    public TextInputControlFotoshop(AEditor editor) { super(editor); }
    
    /**
     * Check if input exists
     * @param input
     * @throws InvalidInputException
     */
    public void inputExists(String input)
            throws InvalidInputException
    {
        if (input.trim().isEmpty())
            throw new InvalidInputException(InternationalizationManager
                    .getInstance().getStringFromResourceBundle("no_input"));
    }
    
    public void wordExists(RequestCommand requestCommand)
            throws InvalidWordException
    {
        if (requestCommand.getWord(0) == null)
            throw new InvalidWordException(InternationalizationManager
                    .getInstance().getStringFromResourceBundle("no_word"));
    }
    
    /**
     * Check if command typed by the input user exists
     * @param requestCommand
     * @throws CommandNotImplementedException
     */
    public void commandExists(RequestCommand requestCommand)
            throws CommandNotImplementedException
    {
        if (this.editor.getCommandExecutor().getCommand() == null)
            throw new CommandNotImplementedException(requestCommand.getWord(0));
    }
}
