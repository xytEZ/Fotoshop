package View.JavaFX.Action;

import Controller.AController;
import Model.Manager.InternationalizationManager;
import View.JavaFX.Controller.FXMLMainSceneController;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

/**
 *
 * @author Fabien PHAM
 */
public final class ZoomActionOnImage extends ActionOnImage
{
    private static final int    DEFAULT_SCALE_X = 1;
    private static final int    DEFAULT_SCALE_Y = 1;
    
    private final String    zoomFailMsg;
    private final String    zoomSuccessMsg;
    private final boolean   increaseScale;
    
    public ZoomActionOnImage(AController controller, 
                             String zoomFailMsg,
                             String zoomSuccessMsg,
                             boolean increaseScale)
    {
        super(controller, "", "");
        this.zoomFailMsg = zoomFailMsg;
        this.zoomSuccessMsg = zoomSuccessMsg;
        this.increaseScale = increaseScale;
    }
    
    @Override
    public void runActionOnImage()
    {
        if (((FXMLMainSceneController)this.controller).getImageView().getImage() == null
                || (!this.increaseScale 
                    && ((FXMLMainSceneController)this.controller)
                            .getImageView().getScaleX() == DEFAULT_SCALE_X
                    && ((FXMLMainSceneController)this.controller)
                            .getImageView().getScaleY() == DEFAULT_SCALE_Y))
            ((FXMLMainSceneController)this.controller).getTextArea()
                      .appendText(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle(this.zoomFailMsg)
                            + System.lineSeparator());
        else
        {
            ScaleTransition st = new ScaleTransition(Duration.millis(1000),
                                                    ((FXMLMainSceneController)this.controller)
                                                            .getImageView());
            
            st.setByX(this.increaseScale ? DEFAULT_SCALE_X : -DEFAULT_SCALE_X);
            st.setByY(this.increaseScale ? DEFAULT_SCALE_Y : -DEFAULT_SCALE_Y);
            st.play();
            ((FXMLMainSceneController)this.controller).getTextArea()
                      .appendText(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle(this.zoomSuccessMsg)
                            + System.lineSeparator());
        }
    }
}