/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.JavaFX;

import View.IView;
import View.JavaFX.Controller.FXMLInputSceneController;
import java.io.IOException;
import java.util.Observable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Fabien PHAM
 */
public class JavaFXInputSceneView implements IView
{
    public enum CloseType { NONE, BUTTON_X, ESCAPE, SUBMIT }
    
    private static final String FXML_RESOURCE = "/View/JavaFX/FXML/FXMLInputScene.fxml";
    
    private final Stage                     owner;
    private String                          text;
    private CloseType                       closeType;
    
    public JavaFXInputSceneView(Stage owner)
    {
        this.owner = owner;
        this.closeType = CloseType.NONE;
    }
    
    @Override
    public void launch()
    {
        Stage       stage = new Stage();
        FXMLLoader  loader = new FXMLLoader(getClass().getResource(FXML_RESOURCE));
        Scene       scene;
        
        try
        {
            scene = new Scene(loader.load());
        }
        catch (final IOException e)
        {
            throw new RuntimeException();
        }
        scene.setOnKeyPressed(event ->
        {
            if (event.getCode() == KeyCode.ESCAPE)
            {
                this.closeType = CloseType.ESCAPE;
                stage.close();
            }
        });
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(this.owner);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> this.closeType = CloseType.BUTTON_X);
        stage.setTitle("Filter");
        stage.showAndWait();
        if (this.closeType == CloseType.NONE)
        {
            this.closeType = CloseType.SUBMIT;
            this.text = ((FXMLInputSceneController)loader
                                                .getController())
                                                .getTextField()
                                                .getText();
            this.text = "\"" + this.text + "\"";
        }
    }

    @Override
    public void display() { }

    @Override
    public void close() { }

    @Override
    public void update(Observable o, Object arg) { }
    
    public CloseType getCloseType() { return this.closeType; }
    
    @Override
    public String toString() { return this.text; }
}
