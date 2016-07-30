package Controller;

import Model.AEditor;
import Model.AModel;

/**
 * Controller for Fotoshop application
 * @author Fabien PHAM
 */
public final class FotoshopController extends AController
{
    /**
     * Initialize the Fotoshop controller and the parent class
     * @param model
     */
    public FotoshopController(AModel model) { super(model); }
    
    @Override
    public void actionPerform(String input)
    {
        ((AEditor)this.model).edit(input);
    }
}