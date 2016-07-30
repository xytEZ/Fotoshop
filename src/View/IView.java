package View;

import java.util.Observer;

/**
 * Interface for future implementation of view derived of the observer
 * pattern for enable easy update of the view by a model class
 * @author Fabien PHAM
 */
public interface IView extends Observer
{
    /**
     * launch the GUI
     */
    public void launch();
    
    /**
     * update the GUI
     */
    public void display();
    
    /**
     * Close the GUI
     */
    public void close();
}
