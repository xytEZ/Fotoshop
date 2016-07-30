package View.ConsoleApp;

import Controller.AController;
import Helper.OPHelper;
import Model.Manager.InternationalizationManager;
import View.IView;
import java.util.Arrays;
import java.util.Observable;
import java.util.Scanner;

/**
 * Console view of the Fotoshop application managing the display
 * @author Fabien PHAM
 */
public class ConsoleAppFotoshopView implements IView
{
    private static final String PROMPT_MESSAGE = "> ";
   
    /**
     * Controller related to the ConsoleAppFotoshopView
     */
    private final AController   controller;
    
    /**
     * Object managing the read of the standard input
     */
    private final Scanner       scanner;
    
    /**
     * Message to print
     */
    private String              text;
    
    /**
     * Boolean for stop the infinite loop
     */
    private boolean             start;
    
    /**
     * Initialize the read of the standard input and the welcome messages
     * of the Fotoshop application
     * @param controller
     */
    public ConsoleAppFotoshopView(AController controller)
    {
        this.controller = controller;
        this.scanner = new Scanner(System.in);   
        this.text = "";
        this.start = false;
        System.out.println(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("language_used")
                            + " : "
                            + InternationalizationManager.getInstance()
                            .getLocation().getLanguage()
                            + System.lineSeparator());
        Arrays.stream(new String[]
        {
            InternationalizationManager.getInstance()
                .getStringFromResourceBundle("welcome_fotoshop"),
            InternationalizationManager.getInstance()
                .getStringFromResourceBundle("welcome_description"),
            InternationalizationManager.getInstance()
                .getStringFromResourceBundle("welcome_help"),
            "",
            InternationalizationManager.getInstance()
                .getStringFromResourceBundle("welcome_current_image"),
            InternationalizationManager.getInstance()
                .getStringFromResourceBundle("welcome_filters")
        }).forEach(System.out::println);
        System.out.println();
    }
    
    /**
     * Run the view
     */
    @Override
    public void launch()
    {
        while (!this.start)
        {
            System.out.print(PROMPT_MESSAGE);
            this.controller.actionPerform(this.scanner.nextLine());
        }
    }
    
    /**
     * Display the text on the console
     */
    @Override
    public void display() { System.out.print(this.text); }
    
    /**
     * Display the messages of shutting of the application
     */
    @Override
    public void close()
    {
        System.out.println(InternationalizationManager.getInstance()
                .getStringFromResourceBundle("close_fotoshop"));
    }

    /**
     * Method called by the model with the observer pattern for
     * display the message on the console
     * @param o
     * @param arg 
     */
    @Override
    public void update(Observable o, Object arg)
    {
        this.start = (boolean)arg;
        this.text = OPHelper.MessageHelper.getInstance().toString();
        display();
        OPHelper.MessageHelper.getInstance().clearMessage();
    }
}
