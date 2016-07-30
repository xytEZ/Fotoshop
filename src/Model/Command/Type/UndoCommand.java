package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;

/**
 * Undo command
 * @author Fabien PHAM
 */
public final class UndoCommand implements ICommand
{
    /**
     * Execute the Undo command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        ImageHolder     imageFolder = fotoshop.getImageManager()
                                            .getImageHolderSelected();
        
        if (imageFolder == null)
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("undo_command_image_no_selected"));
        else
        {
            if (fotoshop.getImageManager().restoreLastMemento(imageFolder))
                OPHelper.MessageHelper.getInstance()
                        .appendMessage("\"Undo\" ")
                        .appendMessage(InternationalizationManager
                                .getInstance()
                                .getStringFromResourceBundle("undo_command_performed"));
            else
                OPHelper.MessageHelper.getInstance()
                        .appendMessage("\"Undo\" ")
                        .appendMessage(InternationalizationManager
                                .getInstance()
                                .getStringFromResourceBundle("undo_command_is_impossible"));
        }
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
