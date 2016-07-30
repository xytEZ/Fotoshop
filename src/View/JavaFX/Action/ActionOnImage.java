package View.JavaFX.Action;

import Controller.AController;
import Helper.OPHelper;
import Model.ColorImage;
import Model.EditableImage;
import Model.Fotoshop;
import Model.ImageHolder;
import View.JavaFX.Controller.FXMLMainSceneController;
import javafx.embed.swing.SwingFXUtils;

/**
 *
 * @author Fabien PHAM
 */
public class ActionOnImage
{
    protected final AController controller;
    protected final String      commandName;
    protected final String      param;
    
    public ActionOnImage(AController controller, String commandName, String param)
    {
        this.controller = controller;
        this.commandName = commandName;
        this.param = param;
    }

    public void runActionOnImage()
    {
        this.controller.actionPerform(this.commandName + " " + this.param);
        
        ImageHolder imageHolder = ((Fotoshop)((FXMLMainSceneController)this.controller)
                                                        .getModel())
                                                        .getImageManager()
                                                        .getImageHolderSelected();
        
        if (imageHolder != null)
            ((FXMLMainSceneController)this.controller).getImageView()
                    .setImage(SwingFXUtils
                            .toFXImage((ColorImage)((EditableImage)imageHolder
                                    .getOriginatorImage()
                                    .getState())
                                    .getCurrentImage(),
                                    null));
        ((FXMLMainSceneController)this.controller).getTextArea()
                .appendText(OPHelper.MessageHelper.getInstance().toString());
    }
}
