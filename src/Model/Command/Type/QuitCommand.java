package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.Manager.InternationalizationManager;

/**
 * Quit command
 * @author Fabien PHAM
 */
public final class QuitCommand implements ICommand
{

    /**
     * Execute the Quit command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        
        if (requestCommand.getWord(1) != null)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("quit_command_quit_what"))
                    .appendMessage(System.lineSeparator());
            return false;
        }
        return true;
    }
}
