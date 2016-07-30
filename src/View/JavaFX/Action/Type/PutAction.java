package View.JavaFX.Action.Type;

import Controller.AController;
import Helper.OPHelper;
import View.JavaFX.Action.IAction;
import View.JavaFX.Controller.FXMLMainSceneController;
import View.JavaFX.JavaFXInputSceneView;
import View.JavaFX.JavaFXInputSceneView.CloseType;

/**
 *
 * @author Fabien PHAM
 */
public final class PutAction implements IAction
{    
    @Override
    public void apply(AController controller)
    {
        JavaFXInputSceneView    inputSceneView = 
                new JavaFXInputSceneView(((FXMLMainSceneController)controller)
                                                                    .getStage());
        
        inputSceneView.launch();
        if (inputSceneView.getCloseType() == CloseType.SUBMIT)
        {
            controller.actionPerform("put" + " " + inputSceneView.toString());
            ((FXMLMainSceneController)controller).
                    getTextArea()
                    .appendText(OPHelper.MessageHelper.getInstance().toString());
        }
    }
}
