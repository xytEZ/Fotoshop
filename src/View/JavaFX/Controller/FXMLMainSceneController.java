package View.JavaFX.Controller;

import Controller.AController;
import Helper.OPHelper;
import View.JavaFX.Action.ActionFactory;
import Model.AEditor;
import Model.AModel;
import View.JavaFX.Action.OpenActionOnImage;
import java.net.URL;
import java.util.ResourceBundle;;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

/**
 *
 * @author Fabien PHAM
 */
public class FXMLMainSceneController extends AController implements Initializable
{
    @FXML private ScrollPane    scrollPaneImageView;
    @FXML private ImageView     imageView;
    @FXML private TextArea      textArea;
    @FXML private MenuItem      menuItemSave;
    @FXML private MenuItem      menuItemUndo;
    @FXML private MenuItem      menuItemLook;
    @FXML private MenuItem      menuItemOpen;
    @FXML private MenuItem      menuItemScript;
    @FXML private MenuItem      menuItemAboutFotoshop;
    @FXML private MenuItem      menuItemFotoshopHelp;
    @FXML private MenuItem      menuItemZoomIn;
    @FXML private MenuItem      menuItemZoomOut;
    @FXML private Button        buttonGet;
    @FXML private Button        buttonMono;
    @FXML private Button        buttonPut;
    @FXML private Button        buttonRot90;
   
    private static AModel   modell;
    private ActionFactory   actionFactory;
    
    public FXMLMainSceneController() { super(FXMLMainSceneController.modell); }
    
    public FXMLMainSceneController(AModel model)
    {
        super(model);
        FXMLMainSceneController.modell = model;
    }
    
    @Override
    public void actionPerform(String input)
    {
        ((AEditor)this.model).edit(input);
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.imageView.fitWidthProperty().bind(this.scrollPaneImageView
                                                   .widthProperty());
        this.imageView.fitHeightProperty().bind(this.scrollPaneImageView
                                                    .heightProperty());
        this.menuItemSave.setDisable(true);
        this.menuItemSave.setOnAction(new MenuItemHandler());
        this.menuItemUndo.setDisable(true);
        this.menuItemUndo.setOnAction(new MenuItemHandler());
        this.menuItemLook.setOnAction(new MenuItemHandler());
        this.menuItemOpen.setOnAction(new MenuItemHandler());
        this.menuItemScript.setOnAction(new MenuItemHandler());
        this.menuItemAboutFotoshop.setOnAction(new MenuItemHandler());
        this.menuItemFotoshopHelp.setOnAction(new MenuItemHandler());
        this.menuItemZoomIn.setOnAction(new MenuItemHandler());
        this.menuItemZoomIn.setDisable(true);
        this.menuItemZoomOut.setOnAction(new MenuItemHandler());
        this.menuItemZoomOut.setDisable(true);
        this.buttonGet.setDisable(true);
        this.buttonGet.setOnAction(new ButtonHandler());
        this.buttonMono.setDisable(true);
        this.buttonMono.setOnAction(new ButtonHandler());
        this.buttonPut.setDisable(true);
        this.buttonPut.setOnAction(new ButtonHandler());
        this.buttonRot90.setDisable(true);
        this.buttonRot90.setOnAction(new ButtonHandler());
        this.actionFactory = ActionFactory.init();
    }
    
    public void setDisableFalseAction()
    {
        this.menuItemSave.setDisable(false);
        this.menuItemUndo.setDisable(false);
        this.menuItemZoomIn.setDisable(false);
        this.menuItemZoomOut.setDisable(false);
        this.buttonGet.setDisable(false);
        this.buttonMono.setDisable(false);
        this.buttonPut.setDisable(false);
        this.buttonRot90.setDisable(false);
    }
    
    public ImageView getImageView() { return this.imageView; }
    public TextArea getTextArea() { return this.textArea; }
    public AModel getModel() { return this.model; }
    public Stage getStage() { return (Stage)this.buttonGet.getScene().getWindow(); }
    
    private class MenuItemHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String  actionText = "";
            
            switch (((MenuItem)event.getSource()).getText())
            {
                case "About Fotoshop" :
                    actionText = "AboutFotoshop";
                    break ;
                case "Fotoshop Help" :
                    actionText = "FotoshopHelp";
                    break ;
                case "Look" :
                    actionText = "Look";
                    break ;
                case "Open..." :
                    actionText = "Open";
                    break ;
                case "Save" :
                    actionText = "Save";
                    break ;
                case "Script" :
                    actionText = "Script";
                    break ;
                case "Undo" :
                    actionText = "Undo";
                    break ;
                case "Zoom in" :
                    actionText = "ZoomIn";
                    break ;
                case "Zoom out" :
                    actionText = "ZoomOut";
                    break ;
            }
            FXMLMainSceneController.this.actionFactory
                            .executeAction(actionText,
                                            FXMLMainSceneController.this);
            OPHelper.MessageHelper.getInstance().clearMessage();
        }
    }
    
    private class ButtonHandler implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String  actionText = "";
            
            switch (((Button)event.getSource()).getText())
            {
                case "Get" :
                    actionText = "Get";
                    break ;
                case "Mono" :
                    actionText = "Mono";
                    break ;
                case "Put" :
                    actionText = "Put";
                    break ;
                case "Rot90" :
                    actionText = "Rot90";
                    break ;
            }
            FXMLMainSceneController.this.actionFactory
                            .executeAction(actionText,
                                            FXMLMainSceneController.this);
            OPHelper.MessageHelper.getInstance().clearMessage();
        }
    }
    
    @FXML
    private void handleDragOver(DragEvent event)
    {
        String  filename = event.getDragboard()
                                .getFiles()
                                .get(0)
                                .toURI()
                                .toString();
        
        if (filename.endsWith(".jpg") || filename.endsWith("*.png")
                || filename.endsWith(".png") || filename.endsWith("*.jpeg"))
        {
            event.acceptTransferModes(TransferMode.COPY);
            event.consume();
        }
    }
    
    @FXML
    private void handleDragDropped(DragEvent event)
    {
        Dragboard   db = event.getDragboard();
        boolean     success = false;
        
        if (db.hasFiles())
        {
            new OpenActionOnImage(this, db.getFiles().get(0))
                    .runActionOnImage();
            OPHelper.MessageHelper.getInstance().clearMessage();
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
