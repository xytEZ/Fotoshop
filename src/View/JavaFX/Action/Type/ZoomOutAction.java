package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.IAction;
import View.JavaFX.Action.ZoomActionOnImage;

/**
 *
 * @author Fabien PHAM
 */
public final class ZoomOutAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        new ZoomActionOnImage(controller, "zoom_out_fail", "zoom_out_success", false)
                .runActionOnImage();
    }
}
