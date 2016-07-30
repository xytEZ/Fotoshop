package Controller;

import Model.AModel;

/**
 * Abstraction class for future implementation of controller
 * @author Fabien PHAM
 */
public abstract class AController
{
    /**
     *  Model class abstract
     */
    protected AModel    model;
    
    /**
     * Initialize abstract controller and add Observer class for model
     * @param model
     */
    protected AController(AModel model) { this.model = model; }

    /**
     * perform the action requested
     * @param input 
     */
    public abstract void actionPerform(String input);
}
