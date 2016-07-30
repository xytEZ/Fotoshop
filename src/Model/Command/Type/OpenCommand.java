package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.ColorImage;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Exception.ImageNotFoundException;
import Model.Fotoshop;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;

/**
 * Open command
 * @author Fabien PHAM
 */
public final class OpenCommand implements ICommand
{
    private static final String HELP_COMMAND = "help";
    
    /**
     * Use the cache of image for avoid the loading of image and 
     * others instructions for accelerate the use of the image file
     * @param fotoshop
     * @param requestCommand
     * @return 
     */
    private boolean openImageAlreadyOpened(final Fotoshop fotoshop,
                                    RequestCommand requestCommand)
    {
        ImageHolder    imageHolder = fotoshop.getImageManager()
                                    .getImageHolderByFilename(requestCommand.getWord(1));

        if (imageHolder == null)
            return false;
        fotoshop.getImageManager().deselectCurrentImageHolder();
        imageHolder.setIsSelected(true);
        OPHelper.MessageHelper.getInstance()
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("open_command_loaded"))
                .appendMessage(" : \"")
                .appendMessage(((EditableImage)fotoshop.getImageManager()
                                       .getImageHolderSelected()
                                       .getOriginatorImage()
                                        .getState())
                                        .getFilename())
                .appendMessage("\"");
        return true;
    }

    /**
     * Open a new image by loading the file image
     * @param fotoshop
     * @param requestCommand 
     */
    private void openNewImage(final Fotoshop fotoshop,
                                RequestCommand requestCommand)
    {
        String      inputName = requestCommand.getWord(1);
        ColorImage  img = null;

        try
        {
            img = (ColorImage)EditableImage.loadImage(inputName);
        }
        catch (final ImageNotFoundException e)
        {
            OPHelper.MessageHelper.getInstance().appendMessage(e.getMessage());
        }
        if (img != null)
        {
            fotoshop.getImageManager().deselectCurrentImageHolder();
            
            ImageHolder imageHolder = new ImageHolder();
            
            imageHolder.getOriginatorImage().setState(new EditableImage(img, inputName));
            fotoshop.getImageManager().addImageHolder(imageHolder);
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("open_command_loaded"))
                    .appendMessage(" \"")
                    .appendMessage(inputName)
                    .appendMessage("\"");
        }
    }
    
    /**
     * Execute the Open command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        
        if (requestCommand.getWord(1) == null)
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("open_command_open_what"));
        else if (!openImageAlreadyOpened(fotoshop, requestCommand))
            openNewImage(fotoshop, requestCommand);
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
