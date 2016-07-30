package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.IAction;
import javafx.scene.control.Alert;

/**
 *
 * @author Fabien PHAM
 */
public final class AboutFotoshopAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        Alert   alert = new Alert(Alert.AlertType.INFORMATION);
        
        alert.setHeaderText("About Fotoshop");
        alert.setContentText("Author : Fabien PHAM (fnp2)");
        alert.show();
    }
}
