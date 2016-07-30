package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.Manager.InternationalizationManager;

/**
 * Help command
 * @author Fabien PHAM
 */
public final class HelpCommand implements ICommand
{

    /**
     * Execute the help command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        
        OPHelper.MessageHelper.getInstance()
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("help_command_using"))
                .appendMessage(System.lineSeparator())
                .appendMessage(System.lineSeparator())
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("help_command_your_command"))
                .appendMessage(System.lineSeparator())
                .appendMessage("   ");
        fotoshop.getCommandWords().getAllStringCommands().stream()
                .forEach(c -> OPHelper.MessageHelper.getInstance()
                                .appendMessage(c)
                                .appendMessage(" "));
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}