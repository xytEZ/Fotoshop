package View.JavaFX.Action;

import Controller.AController;
import Helper.OPHelper;
import View.JavaFX.Controller.FXMLMainSceneController;
import java.io.File;
import javafx.scene.image.Image;

/**
 *
 * @author Fabien PHAM
 */
public final class OpenActionOnImage extends ActionOnImage
{
    private final File  file;
    
    public OpenActionOnImage(AController controller, File file)
    {
        super(controller, "open", file.toString());
        this.file = file;
    }
    
    @Override
    public void runActionOnImage()
    {
        this.controller.actionPerform("open" + " " + this.param);
        ((FXMLMainSceneController)this.controller)
                .getTextArea().appendText(OPHelper.MessageHelper.getInstance().toString());
        ((FXMLMainSceneController)this.controller)
                .getImageView().setImage(new Image(this.file.toURI().toString()));
        ((FXMLMainSceneController)this.controller).setDisableFalseAction();
    }
}
