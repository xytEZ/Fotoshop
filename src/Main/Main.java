package Main;

import Controller.AController;
import Controller.FotoshopController;
import Model.AModel;
import Model.ColorImage;
import Model.Fotoshop;
import Model.Manager.InternationalizationManager;
import View.ConsoleApp.ConsoleAppFotoshopView;
import View.IView;
import View.JavaFX.Controller.FXMLMainSceneController;
import View.JavaFX.JavaFXFotoshopView;

/**
 * Main class
 * @author Fabien PHAM
 */
public class Main
{
    /**
     * Description and error message for serious mistake in the program
     * @param e 
     */
    private static void errorMessage(final Exception e)
    {
        System.err.println("A dysfunction is occurred during the program."
                        + System.lineSeparator()
                        + "Please analyze the following errors : "
                        + System.lineSeparator()
                        + "------------------------------------------------------"
                        + System.lineSeparator());
        e.printStackTrace();
    }

    /**
     * Initialization of the internationalization and the MVC pattern 
     * for Fotoshop application. We can define the arguments with the "args"
     * variable for choose the right language and country
     * @param args
     */
    public static void main(String[] args)
    {
        try
        {   
            if (args.length < 2)
                InternationalizationManager.getInstance();
            else
                InternationalizationManager.getInstance(args[0], args[1]);
                       
            AModel      model = new Fotoshop<String, ColorImage>();
            AController controller = new FXMLMainSceneController(model);
            IView       view = new JavaFXFotoshopView();
            
            model.addObserver(view);
            view.launch();
            view.close();
        }
        catch (final Exception e)
        {
            errorMessage(e);
        }
        catch (final VirtualMachineError e)
        {
            System.err.println("An serious error is occurred with memory");
        }
    }
}
