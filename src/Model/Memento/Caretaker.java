package Model.Memento;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * A caretaker class wrapping and managing the old states of a element
 * @author Fabien PHAM
 * @param <T>
 */
public class Caretaker<T> implements Cloneable
{
    /**
     * List containing the old states of a element
     */
    private List<IMemento<T>> savedStates;
    
    /**
     * Initialize the caretaker
     */
    public Caretaker() { this.savedStates = new LinkedList<>(); }
    
    /**
     * Add a memento of a element
     * @return 
     * @throws java.lang.CloneNotSupportedException 
     */
    
    /**
     * Clone the Caretaker class
     * @return
     * @throws CloneNotSupportedException 
     */
    @Override
    public Object clone() 
            throws CloneNotSupportedException
    {
        Caretaker<T>    caretaker = null;
        
        try
        {
            caretaker = (Caretaker<T>)super.clone();
            caretaker.savedStates = (List<IMemento<T>>)((LinkedList<IMemento<T>>)this.savedStates)
                                    .clone();
        }
        catch (final CloneNotSupportedException e)
        {
            throw new RuntimeException(e.getCause());
        }
        return caretaker;
    }
    
    /**
     * Add a memento to the list
     * @param m 
     */
    public void addMemento(IMemento<T> m) { this.savedStates.add(m); }
    
    /**
     * Get a memento of a element and remove it of the list
     * @param index
     * @return
     */
    public IMemento<T> getMemento(int index) { return this.savedStates.get(index); }
    
    /**
     * Get size of all mementos
     * @return
     */
    public int getSavedStatesSize() { return this.savedStates.size(); }
    
    /**
     * Check if list of saved states is empty
     * @return 
     */
    public boolean isEmpty() { return this.savedStates.isEmpty(); }
    
    /**
     * Execution an action of each element of the saved states list
     * @param action 
     */
    public void foreach(Consumer<? super IMemento<T>> action)
    {
        this.savedStates.stream().forEach(action);
    }
    
    /**
     * Get the index of a memento depending on a lambda expression
     * and a element for the comparison
     * @param <U>
     * @param biPredicate
     * @param element
     * @return 
     */
    public <U> Optional<IMemento<T>>
        getIndexElementByEquality(BiPredicate<IMemento<T>, U> biPredicate,
                                  U element)
    {
        return this.savedStates.stream()
                .filter(m -> biPredicate.test(m, element))
                .findFirst();
    }
    
    /**
     * Replace one element
     * @param memento
     * @param index 
     */
    public void overwriteElement(IMemento<T> memento, int index)
    {
        this.savedStates.set(index, memento);
    }
    
    public int indexOf(IMemento<T> memento)
    {
        return this.savedStates.indexOf(memento);
    }
}
