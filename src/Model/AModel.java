package Model;

import java.util.Observable;

/**
 * Abstraction class for future implementation of model by using the 
 * pattern Observable for update the view
 * @author Fabien PHAM
 */
public abstract class AModel extends Observable
{

    /**
     * Initialize model
     */
    protected AModel() { }
}
