package Model.Manager;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
/**
 * A manager class containing the filters applied of each image opened
 * @author Fabien PHAM
 * @param <T>
 */
public final class FilterManager<T> implements Cloneable
{
    /**
     * List of filters applied to image
     */
    private List<T> filters;
    
    /**
     * Initialize the manager class
     */
    public FilterManager() { this.filters = new LinkedList<>(); }
    
    /**
     * Clone the FilterManager class
     * @return 
     */
    @Override
    public Object clone()
    {
        FilterManager<T>    filterManager = null;
        
        try
        {
            filterManager = (FilterManager<T>)super.clone();
            filterManager.filters = (List<T>)((LinkedList<T>)this.filters).clone();
        }
        catch (final CloneNotSupportedException e)
        {
            throw new RuntimeException(e.getCause());
        }
        return filterManager;
    }
    
    /**
     * Check if there are a filter
     * @return 
     */
    public boolean isEmpty() { return this.filters.isEmpty(); }
    
    /**
     * Get the size of filters
     * @return
     */
    public int getSize() { return this.filters.size(); }
    
    /**
     * Get one filter
     * @param index
     * @return
     */
    public T getFilter(int index)
    {
        return (index < getSize()) ? this.filters.get(index) : null;
    }
    
    /**
     * Add new filter
     * @param filter
     */
    public void addFilter(T filter) { this.filters.add(filter); }
    
    /**
     * Method for delegate the task to make action
     * for each element to manager
     * @param action
     */
    public void forEach(Consumer<? super T> action)
    {
        this.filters.stream().forEach(action);
    }
}