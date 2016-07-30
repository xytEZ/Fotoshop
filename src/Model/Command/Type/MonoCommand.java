package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.ColorImage;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;
import java.awt.Color;

/**
 * Mono command
 * @author Fabien PHAM
 */
public final class MonoCommand implements ICommand
{
    private static final String   MONO_COMMAND = "mono";
    
    /**
     * Perform changing on the image file
     * @param wrapperImage 
     */
    private void performChangeOnImage(ImageHolder imageHolder)
    {
        ColorImage      tmpImage = ColorImage
                                    .getNewInstance(((EditableImage)imageHolder
                                                    .getOriginatorImage()
                                                    .getState())
                                                    .getCurrentImage());
                                                         
        int         height = tmpImage.getHeight();
        int         width = tmpImage.getWidth();

        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                Color   pix = tmpImage.getPixel(x, y);
                int     lum = (int)Math.round(0.299*pix.getRed()
                                         + 0.587*pix.getGreen()
                                         + 0.114*pix.getBlue());
                tmpImage.setPixel(x, y, new Color(lum, lum, lum));
            }
        }
        ((EditableImage)imageHolder.getOriginatorImage().getState())
                .setCurrentImage(tmpImage);
    }
    
    /**
     * Execute the Mono command
     * @param model
     * @param requestCommand
     * @return
     */
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
                            .getStringFromResourceBundle("mono_command_image_no_selected"));
        else
        {
            fotoshop.getImageManager().saveToMemento(imageHolder);
            performChangeOnImage(imageHolder);
            OPHelper.MessageHelper.getInstance()
                    .appendMessage("\"Mono\" ")
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("mono_command_performed"));
        }
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
