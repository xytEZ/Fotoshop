package View.JavaFX.Action;

import View.JavaFX.Action.Type.*;
import View.JavaFX.Controller.FXMLMainSceneController;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Fabien PHAM
 */
public final class ActionFactory
{
    private final Map<String, IAction>  actions;
    
    private ActionFactory() { this.actions = new HashMap<>(); }
    
    public static ActionFactory init()
    {
        ActionFactory   actionFactory = new ActionFactory();
        
        actionFactory.addAction("AboutFotoshop", new AboutFotoshopAction());
        actionFactory.addAction("FotoshopHelp", new FotoshopHelpAction());
        actionFactory.addAction("Get", new GetAction());
        actionFactory.addAction("Look", new LookAction());
        actionFactory.addAction("Mono", new MonoAction());
        actionFactory.addAction("Open", new OpenAction());
        actionFactory.addAction("Put", new PutAction());
        actionFactory.addAction("Rot90", new Rot90Action());
        actionFactory.addAction("Save", new SaveAction());
        actionFactory.addAction("Script", new ScriptAction());
        actionFactory.addAction("Undo", new UndoAction());
        actionFactory.addAction("ZoomIn", new ZoomInAction());
        actionFactory.addAction("ZoomOut", new ZoomOutAction());
        return actionFactory;
    }
    
    public void addAction(String name, IAction action)
    {
        this.actions.put(name, action);
    }
    
    public void executeAction(String name,
                                FXMLMainSceneController controller)
    {
        if (this.actions.containsKey(name))
            this.actions.get(name).apply(controller);
    }
}
