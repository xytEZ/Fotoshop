package View.JavaFX.Action.Type;

import Controller.AController;
import Helper.OPHelper;
import View.JavaFX.Action.IAction;
import javafx.scene.control.Alert;

/**
 *
 * @author Fabien PHAM
 */
public final class FotoshopHelpAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        controller.actionPerform("help");
        
        Alert   alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setHeaderText("How to use Fotoshop");
        alert.setContentText(OPHelper.MessageHelper.getInstance().toString()
                                + System.lineSeparator()
                                + "Other features are availabe :"
                                + System.lineSeparator()
                                + "- Zoom in/out image"
                                + System.lineSeparator()
                                + "- Drag and drop");
        alert.show();
    }
}
