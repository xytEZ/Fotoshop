package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;

/**
 * Put command
 * @author Fabien PHAM
 */
public final class PutCommand implements ICommand
{
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        ImageHolder     imageHolder = fotoshop.getImageManager()
                                            .getImageHolderSelected();
        
        if (imageHolder == null)
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("put_command_image_no_selected"));
        else if (requestCommand.getWord(1) == null)
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("put_command_put_what"));
        else
        {
            imageHolder.getCacheImageManager()
                    .addImageInCache(imageHolder, requestCommand.getWord(1));
            OPHelper.MessageHelper.getInstance()
                    .appendMessage("\"Put\" ")
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("put_command_performed"));
        }
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
