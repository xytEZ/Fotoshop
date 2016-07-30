package View.JavaFX;

import Controller.AController;
import View.JavaFX.Controller.FXMLMainSceneController;
import View.IView;
import java.util.Observable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Fabien PHAM
 */
public class JavaFXFotoshopView extends Application implements IView
{
    private static final String FXML_RESOURCE = "/View/JavaFX/FXML/FXMLMainScene.fxml";
    
    public JavaFXFotoshopView() { }
    
    @Override
    public void launch() { Application.launch(); }

    @Override
    public void display() { }

    @Override
    public void close() { Platform.exit(); }

    @Override
    public void update(Observable o, Object arg) { }
    
    @Override
    public void start(Stage stage)
            throws Exception
    {
        FXMLLoader              loader = new FXMLLoader(getClass()
                                                        .getResource(FXML_RESOURCE));
        Scene                   scene = new Scene(loader.load());
        FXMLMainSceneController FXMLController = loader.getController();
    
        FXMLController.getModel().addObserver(this);
        stage.setScene(scene);
        stage.setTitle("Fotoshop");
        stage.show();
    }
}