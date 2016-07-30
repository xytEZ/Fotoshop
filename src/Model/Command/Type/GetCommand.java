package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;

/**
 * Get command
 * @author Fabien PHAM
 */
public final class GetCommand implements ICommand
{
    private void performGetCache(ImageHolder imageHolder,
                                    RequestCommand requestCommand)
    {
        EditableImage   image = imageHolder.getCacheImageManager()
                            .getImageCacheByFilename(requestCommand.getWord(1));
        
        if (image == null)
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                    .getStringFromResourceBundle("get_command_cache_no_found"));
        else
        {
            imageHolder.getOriginatorImage().setState(image);
            OPHelper.MessageHelper.getInstance()
                    .appendMessage("\"Get\" ")
                    .appendMessage(InternationalizationManager.getInstance()
                    .getStringFromResourceBundle("get_command_performed"));
        }
    }
    
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
                            .getStringFromResourceBundle("get_command_get_what"));
        else
            performGetCache(imageHolder, requestCommand);
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
