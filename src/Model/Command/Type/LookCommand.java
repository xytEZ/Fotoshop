package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Model.Fotoshop;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Manager.InternationalizationManager;
import Model.Memento.IMemento;

/**
 * Look command
 * @author Fabien PHAM
 */
public final class LookCommand implements ICommand
{
    
    /**
     * Get informations about each image file opened in Fotoshop
     * @param fotoshop
     * @param wrapperImage 
     */
    private void getInformations(final Fotoshop fotoshop,
                                ImageHolder imageHolder)
    {
        OPHelper.MessageHelper.getInstance()
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("look_command_name"))
                .appendMessage(" : \"")
                .appendMessage(((EditableImage)imageHolder.getOriginatorImage()
                                .getState()).getFilename())
                .appendMessage("\"")
                .appendMessage(", ")
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("look_command_selected"))
                .appendMessage(" : ");
        if (imageHolder.getIsSelected())
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("look_command_yes"));
        else
            
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("look_command_no"));
        OPHelper.MessageHelper.getInstance()
                .appendMessage(", ")
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("look_command_filters_applied"))
                .appendMessage(" : ");
        if (((EditableImage)imageHolder.getOriginatorImage().getState())
                .getFilters().isEmpty())
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("look_command_none"));
        else
            ((EditableImage)imageHolder.getOriginatorImage().getState()).getFilters()
                            .forEach(f -> OPHelper.MessageHelper.getInstance()
                                            .appendMessage((String)f)
                                            .appendMessage(" "));
        OPHelper.MessageHelper.getInstance()
                .appendMessage(", ")
                .appendMessage(InternationalizationManager.getInstance()
                        .getStringFromResourceBundle("look_command_cache"))
                .appendMessage(" : ");
        if (imageHolder.getCacheImageManager().isEmpty())
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("look_command_none"));
        else
            imageHolder.getCacheImageManager()
                    .forEach(i -> OPHelper.MessageHelper.getInstance()
                    .appendMessage("\"")
                    .appendMessage(((EditableImage)((IMemento)i).getSavedState()).getFilename())
                    .appendMessage("\" "));
        OPHelper.MessageHelper.getInstance()
                .appendMessage(System.lineSeparator());
    }
    
    /**
     * Execute the Look command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        
        if (fotoshop.getImageManager().isEmpty())
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("look_command_image_no_loaded"))
                    .appendMessage(System.lineSeparator());
        }
        else
            fotoshop.getImageManager()
                    .getValues()
                    .stream()
                    .forEach(i -> getInformations(fotoshop, (ImageHolder)i));
        return false;
    }
}