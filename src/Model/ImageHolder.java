package Model;

import Model.Manager.CacheImageManager;
import Model.Memento.Originator;
import java.awt.image.BufferedImage;

/**
 * Class containing the current image and the manager's cache
 * @author Fabien PHAM
 * @param <T>
 * @param <U>
 */
public final class ImageHolder<T, U extends BufferedImage>
{
    /**
     * Class managing the current image and the load/save of memento 
     */
    private final Originator<EditableImage<T, U>>        originatorImage;
    
    /**
     * The manager's cache image
     */
    private final CacheImageManager<EditableImage<T, U>> cacheImageManager;
    
    /**
     * variable for select the image
     */
    private boolean                              isSelected;
    
    /**
     * Initialize the ImageHolder
     */
    public ImageHolder()
    {
        this.originatorImage = new Originator<>();
        this.cacheImageManager = new CacheImageManager<>();
        this.isSelected = false;
    }
    
    /**
     * Get the originator image
     * @return 
     */
    public Originator<EditableImage<T, U>> getOriginatorImage() { return this.originatorImage; }
    
    /**
     * Get the manager's cache image
     * @return 
     */
    public CacheImageManager<EditableImage<T, U>> getCacheImageManager() { return this.cacheImageManager; }
    
    /**
     * Get isSelected
     * @return 
     */
    public boolean getIsSelected() { return this.isSelected; }
    
    /**
     * Set isSelected
     * @param isSelected 
     */
    public void setIsSelected(boolean isSelected) { this.isSelected = isSelected; }
}
