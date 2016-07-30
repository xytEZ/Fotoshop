package Model.Manager;

import Exception.RestoreMementoException;
import Model.EditableImage;
import Model.ImageHolder;
import Model.Memento.Caretaker;
import Model.Memento.IMemento;
import Model.Memento.Originator;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * Class managing the cache of image
 * @author Fabien PHAM
 * @param <T>
 */
public final class CacheImageManager<T>
{
    /**
     * Class encapsulating the list of cache image
     */
    private final Caretaker<T>                      cacheImage;
    
    /**
     * BiPredicate for find the right memento depending on filename
     */
    private final BiPredicate<IMemento<T>, String>  biPredicate;
    
    /**
     * Initialize the CacheImageManager
     */
    public CacheImageManager()
    {
        this.cacheImage = new Caretaker<>();
        this.biPredicate = 
                (i, s) -> ((EditableImage)i.getSavedState())
                                                .getFilename().equals(s);
    }
    
    /**
     * Check if cache of image is empty
     * @return 
     */
    public boolean isEmpty() { return this.cacheImage.isEmpty(); }
    
    /**
     * Add a new image in the cache of image
     * @param imageHolder
     * @param filename 
     */
    public void addImageInCache(ImageHolder imageHolder, String filename)
    {
        IMemento                memento = imageHolder.getOriginatorImage()
                                                     .saveToMemento();
        Optional<IMemento<T>>   optional = 
                this.cacheImage.getIndexElementByEquality(this.biPredicate,
                                                          filename);
        
        ((EditableImage)memento.getSavedState()).setFilename(filename);
        if (optional.isPresent())
            this.cacheImage.overwriteElement(memento,
                                            this.cacheImage
                                                    .indexOf(optional.get()));
        else
            this.cacheImage.addMemento(memento);
    }
    
    /**
     * Get a image of cache by the filename parameter
     * @param filename
     * @return 
     */
    public EditableImage getImageCacheByFilename(String filename)
    {
        Optional<IMemento<T>>   optional = 
                this.cacheImage.getIndexElementByEquality(this.biPredicate,
                                                          filename);

        if (optional.isPresent())
        {
            Originator  originator = new Originator();

            try
            {
                originator.restoreFromMemento(optional.get());
                return (EditableImage)originator.getState();
            }
            catch (final RestoreMementoException e) { }
        }
        return null;
    }
    
    /**
     * Execute a action for each image of cache
     * @param action 
     */
    public void forEach(Consumer<? super IMemento<T>> action)
    {
        this.cacheImage.foreach(action);
    }
}
