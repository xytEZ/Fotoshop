package Model;

/**
 *
 * @author Fabien PHAM
 */
public abstract class ATextInputControlEditor
{
    /**
     * Reference on actual editor used
     */
    protected final AEditor editor;
    
    protected ATextInputControlEditor(AEditor editor) { this.editor = editor; }
}
