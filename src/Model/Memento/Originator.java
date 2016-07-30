package Model.Memento;

import Exception.RestoreMementoException;
import java.lang.reflect.InvocationTargetException;

/**
 * Backup and restoration class using Memento pattern
 * @author Fabien PHAM
 * @param <T>
 */
public class Originator<T>
{
    /**
     * Generic element storing reference of element
     */
    private T   state;
    
    /**
     * Get the element
     * @return
     */
    public T getState() { return this.state; }
    
    /**
     * Set the element
     * @param state
     */
    public void setState(T state) { this.state = state; }
    
    /**
     * Save the actual element and transform it to memento class
     * for keep a version of element
     * @return
     */
    public IMemento<T> saveToMemento()
    {
        try
        {
            return new Memento<>(this.state);
        }
        catch (final ReflectiveOperationException e)
        {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * Restore the current element by making a update with get
     * a memento of the current element
     * @param m
     * @throws RestoreMementoException
     */
    public void restoreFromMemento(IMemento<T> m)
            throws RestoreMementoException
    {
        if (m == null)
            throw new RestoreMementoException();
        this.state = (T)m.getSavedState();
    }
    
    /**
     * Class wrapping the state of a element by cloning the referenced element
     * @param <T> 
     */
    private static class Memento<T> implements IMemento<T>
    {
       private final T  state;
       
       public Memento(T stateToSave) 
               throws NoSuchMethodException, 
                        IllegalAccessException, 
                        IllegalArgumentException, 
                        InvocationTargetException
       {
           this.state = (T)stateToSave.getClass()
                                        .getMethod("clone")
                                        .invoke(stateToSave);
       }
       
       /**
        * Get the saved state of element
        * @return 
        */
       @Override
       public T getSavedState() { return this.state; }
    }
}