package View.JavaFX.Action.Type;

import Controller.AController;
import View.JavaFX.Action.ActionOnImage;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;
import View.JavaFX.JavaFXInputSceneView;
import View.JavaFX.JavaFXInputSceneView.CloseType;

/**
 *
 * @author Fabien PHAM
 */
public final class GetAction implements IAction
{
    @Override
    public void apply(AController controller)
    {
        JavaFXInputSceneView    inputSceneView =
                new JavaFXInputSceneView(((FXMLMainSceneController)controller)
                                                                .getStage());
        
        inputSceneView.launch();
        if (inputSceneView.getCloseType() == CloseType.SUBMIT)
            new ActionOnImage(controller, "get", inputSceneView.toString())
                    .runActionOnImage();
    }
}
