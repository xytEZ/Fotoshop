package View.JavaFX.Controller;

import Controller.AController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Fabien PHAM
 */
public class FXMLInputSceneController extends AController implements Initializable
{
    @FXML private TextField textField;
    @FXML private Button    buttonSubmit;
    
    public FXMLInputSceneController() { super(null); }
    
    @Override
    public void actionPerform(String input) { }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        this.textField.setOnKeyReleased(event ->
        {
            String  text = ((TextField)event.getSource()).getText();
            
            FXMLInputSceneController.this
                    .buttonSubmit
                    .setDisable(text.isEmpty() || text.trim().isEmpty());
        });
        this.textField.textProperty().addListener(
        (ObservableValue<? extends String> observableValue, String oldValue, String newValue) ->
        {
            if (newValue.contains("\""))
                FXMLInputSceneController.this.textField.setText(oldValue);
        });
        this.buttonSubmit.setDisable(true);
        this.buttonSubmit.setDefaultButton(true);
        this.buttonSubmit.setOnAction(event ->
        {
            ((Stage)this.buttonSubmit.getScene().getWindow()).close();
        });
    }
    
    public TextField getTextField() { return this.textField; }
    public Button getButtonSubmit() { return this.buttonSubmit; }
}
