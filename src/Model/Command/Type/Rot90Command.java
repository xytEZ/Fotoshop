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
 * Rot90 command
 * @author Fabien PHAM
 */
public final class Rot90Command implements ICommand
{
    private static final String ROT90_COMMAND = "rot90";
    
    /**
     * Perform changing on the image by making a rotation of 90 degrees
     * @param imageHolder 
     */
    private void performChangeOnImage(ImageHolder imageHolder)
    {
        ColorImage      tmp = (ColorImage)((EditableImage)imageHolder
                                            .getOriginatorImage()
                                            .getState())
                                            .getCurrentImage();                           
        int             height = tmp.getHeight();
        int             width = tmp.getWidth();
        ColorImage      rotImage = ColorImage.getNewInstance(height, width);

        for (int y = 0; y < height; ++y)
        {
            for (int x = 0; x < width; ++x)
            {
                Color   pix = ((ColorImage)((EditableImage)imageHolder
                                    .getOriginatorImage()
                                    .getState())
                                    .getCurrentImage())
                                    .getPixel(x, y);

                rotImage.setPixel(height - y - 1, x, pix);
            }
        }
        ((EditableImage)imageHolder.getOriginatorImage().getState())
                .setCurrentImage(rotImage);
        ((EditableImage)imageHolder.getOriginatorImage().getState())
                .getFilters().addFilter(ROT90_COMMAND);
    }
    
    /**
     * Execute the Rot90 command
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
                            .getStringFromResourceBundle("rot90_command_image_no_selected"));
        else
        {
            fotoshop.getImageManager().saveToMemento(imageHolder);
            performChangeOnImage(imageHolder);
            OPHelper.MessageHelper.getInstance()
                    .appendMessage("\"Rot90\" ")
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("rot90_command_performed"));
        }
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}
