package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.OpenActionOnImage;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Fabien PHAM
 */
public final class OpenAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        FileChooser                 fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterImage = new FileChooser
                .ExtensionFilter("All images",
                                "*.jpg", "*.png", "*.gif", "*.jpeg");

        fileChooser.getExtensionFilters().add(extFilterImage);
        
        File    file = fileChooser
                        .showOpenDialog(((FXMLMainSceneController)controller)
                                                                .getStage());

        if (file != null)
            new OpenActionOnImage(controller, file).runActionOnImage();
    }
}
