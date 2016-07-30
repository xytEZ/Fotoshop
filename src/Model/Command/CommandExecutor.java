package Model.Command;

import Model.AModel;

/**
 * Class managing execution command
 *
 * @author Fabien PHAM
 */
public final class CommandExecutor
{
    /**
     * Interface for enable the change of command during the execution time
     * of the application
     */
    private ICommand    command;
    
    /**
     * Initialize the CommandExecutor
     */
    public CommandExecutor() { this.command = null; }
    
    /**
     * Execute the right command contained in the interface command
     * @param model
     * @param requestCommand
     * @return
     */
    public boolean executeCommand(final AModel model,
                                    RequestCommand requestCommand)
    {
        return this.command.execute(model, requestCommand);
    }

    /**
     * Set the command class
     * @param command
     */
    public void setCommand(ICommand command) { this.command = command; }
    
    /**
     * Get the command class
     * @return
     */
    public ICommand getCommand() { return this.command; }
}
