package Model.Command;

import Model.AModel;

/**
 * Interface for forced future implementation of command based on the 
 * pattern "Injection of Dependency" and consequently, strategy pattern
 * @author Fabien PHAM
 */
public interface ICommand
{

    /**
     * Method for execute the command who will be overridden by
     * the futures commands class
     * @param model
     * @param requestCommand
     * @return
     */
    public boolean execute(final AModel model, RequestCommand requestCommand);
}
