package Model.Memento;

/**
 * Interface for forced a future programmer to implement method getSavedStates
 * in order to allow the creation of new memento thanks to the caretaker class
 * with the Memento pattern
 * forced user 
 * @author Fabien PHAM
 * @param <T>
 */
public interface IMemento<T>
{

    /**
     * Get the old state of object
     * @return
     */
    public T getSavedState();
}
