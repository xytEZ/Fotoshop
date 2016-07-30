package View.JavaFX.Action.Type;

import Controller.AController;
import Helper.OPHelper;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;

/**
 *
 * @author Fabien PHAM
 */
public final class LookAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        controller.actionPerform("look");
        ((FXMLMainSceneController)controller).getTextArea()
                                             .appendText(OPHelper
                                                        .MessageHelper
                                                        .getInstance()
                                                        .toString());
    }
}
