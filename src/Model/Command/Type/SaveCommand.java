package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.CommandWordsFotoshop;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Save command
 * @author Fabien PHAM
 */
public final class SaveCommand implements ICommand
{
    private static final String HELP_COMMAND = "help";
    private static final String FORMAT_NAME = "jpg";
    
    /**
     * Perform the save on the state of the selected image
     * @param fotoshop
     * @param imageHolder
     * @param requestCommand 
     */
    private void performSaveCurrentImage(final Fotoshop fotoshop,
                                        ImageHolder imageHolder,
                                        RequestCommand requestCommand)
    {
        String  outputName = requestCommand.getWord(1);
            
        try
        {
            File    outputFile = new File(outputName);

            if (outputFile.exists()
                    && (outputFile.isDirectory() || !outputFile.canWrite()))
                throw new IOException();
            outputFile.createNewFile();
            if (!outputFile.exists())
                throw new FileNotFoundException();
            ImageIO.write(((EditableImage)imageHolder.getOriginatorImage().getState())
                                .getCurrentImage(),
                            FORMAT_NAME, outputFile);
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("save_command_image_saved_to"))
                    .appendMessage(" \"")
                    .appendMessage(outputName)
                    .appendMessage("\"");
        }
        catch (final IOException e)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("save_command_cannot_save"));
        }
    }
    
    /**
     * Execute the Save command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        ImageHolder    imageHolder = fotoshop.getImageManager()
                                        .getImageHolderSelected();
        
        if (imageHolder == null)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("save_command_image_no_selected"));
        }
        else if (requestCommand.getWord(1) == null)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("save_command_save_where"));
        }
        else
            performSaveCurrentImage(fotoshop, imageHolder, requestCommand);
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
        return false;
    }
}