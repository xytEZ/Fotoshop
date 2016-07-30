package View.JavaFX.Action.Type;

import Controller.AController;
import Helper.OPHelper;
import Model.EditableImage;
import Model.Fotoshop;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;
import java.io.File;
import java.io.IOException;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 *
 * @author Fabien PHAM
 */
public final class SaveAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        FileChooser                     fileChooser = new FileChooser();
        FileChooser.ExtensionFilter[]   extFilterImage = new FileChooser
                                                            .ExtensionFilter[]
        {
            new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg"),
            new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"),
            new FileChooser.ExtensionFilter("GIF files (*.gif)", "*.gif"),
            new FileChooser.ExtensionFilter("JPEG files (*.jpeg)", "*.jpeg")
        };
        
        fileChooser.getExtensionFilters().addAll(extFilterImage);
        
        File    file = fileChooser
                .showSaveDialog(((FXMLMainSceneController)controller).getStage());
        
        if (file != null)
        {
            String  name = file.getName();
            
            try
            {
                ImageIO.write(((EditableImage)((Fotoshop)
                                ((FXMLMainSceneController)controller)
                                .getModel())
                                .getImageManager()
                                .getImageHolderSelected()
                                .getOriginatorImage()
                                .getState())
                                .getCurrentImage(),
                                name.substring(name.lastIndexOf(".") + 1),
                                file);
                controller.actionPerform("save" + " " + file.getPath());
                ((FXMLMainSceneController)controller)
                        .getTextArea()
                        .appendText(OPHelper.MessageHelper.getInstance().toString());
            }
            catch (final IOException e) { }
        }
    }
}
