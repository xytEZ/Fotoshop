package View.JavaFX.Action.Type;

import Controller.AController;
import Model.Fotoshop;
import View.JavaFX.Action.ActionOnImage;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;
import java.io.File;
import javafx.stage.FileChooser;

/**
 *
 * @author Fabien PHAM
 */
public final class ScriptAction implements IAction
{

    @Override
    public void apply(AController controller)
    {
        FileChooser                 fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterText = new FileChooser
                .ExtensionFilter("TEXT (*.txt)", "*.txt");
        
        fileChooser.getExtensionFilters().add(extFilterText);
        
        File    file = fileChooser
                .showOpenDialog(((FXMLMainSceneController)controller).getStage());
        
        if (file != null)
        {
            new ActionOnImage(controller, "script", file.toString())
                    .runActionOnImage();
            if (((Fotoshop)((FXMLMainSceneController)controller).getModel())
                    .getImageManager().getImageHolderSelected() != null)
                ((FXMLMainSceneController)controller).setDisableFalseAction();
        }
    }
}
