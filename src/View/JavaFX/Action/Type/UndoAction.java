package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.ActionOnImage;
import View.JavaFX.Action.IAction;

/**
 *
 * @author Fabien PHAM
 */
public final class UndoAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        new ActionOnImage(controller, "undo", "").runActionOnImage();
    }
}
