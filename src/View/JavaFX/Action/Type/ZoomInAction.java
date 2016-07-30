package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.IAction;
import View.JavaFX.Action.ZoomActionOnImage;

/**
 *
 * @author Fabien PHAM
 */
public final class ZoomInAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        new ZoomActionOnImage(controller, "zoom_in_fail", "zoom_in_success", true)
                .runActionOnImage();
    }
}
