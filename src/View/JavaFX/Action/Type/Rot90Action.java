package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.ActionOnImage;
import View.JavaFX.Action.IAction;

/**
 *
 * @author Fabien PHAM
 */
public final class Rot90Action implements IAction
{
    @Override
    public void apply(AController controller)
    {
        new ActionOnImage(controller, "rot90", "").runActionOnImage();
    }
}
