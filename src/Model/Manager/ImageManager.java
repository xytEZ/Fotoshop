package Model.Manager;

import Exception.RestoreMementoException;
import Model.ImageHolder;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The manager's image containing the current images 
 * already opened but also, for restitute the old states after
 * modifications performed on a image
 * 
 * @author Fabien PHAM
 * @param <T>
 * @param <U>
 */
public final class ImageManager<T, U extends BufferedImage>
{
    /**
     * Use of the map for accelerate the research of a image already opened.
     * The map contains the filename as key and a WrapperImage as value
     */
    private final Map<String, ImageHolder<T, U>> imageHolders;
    
    /**
     * Initialize the image's manager
     */
    public ImageManager() { this.imageHolders = new HashMap<>(); }
    
    /**
     * Check if there are already a image opened
     * @return 
     */
    public boolean isEmpty() { return this.imageHolders.isEmpty(); }
    
    /**
     * Get all the images already opened
     * @return 
     */
    public Collection<ImageHolder<T, U>> getValues()
    {
        return this.imageHolders.values();
    }
    
    /**
     * Get the image actually selected
     * @return 
     */
    public ImageHolder<T, U> getImageHolderSelected()
    {
        return (!this.imageHolders.isEmpty()) ?
                this.imageHolders.entrySet()
                                .stream()
                                .filter(i -> i.getValue().getIsSelected())
                                .map(i -> i.getValue())
                                .findFirst()
                                .get()
                : null;
    }
    
    /**
     * Get a image by filename
     * @param filename
     * @return 
     */
    public ImageHolder<T, U> getImageHolderByFilename(String filename)
    {
        if (this.imageHolders.isEmpty())
            return null;
        
        Optional<ImageHolder<T, U>>    optional = 
                this.imageHolders
                .entrySet()
                .stream()
                .filter(e -> e.getKey().equals(filename))
                .map(e -> e.getValue())
                .findFirst();
        
        return (optional.isPresent()) ? optional.get() : null;
    }
    
    /**
     * Add a image in the manager's image
     * @param imageHolder 
     */
    public void addImageHolder(ImageHolder<T, U> imageHolder)
    {
        this.imageHolders.put(imageHolder
                                .getOriginatorImage()
                                .getState()
                                .getFilename(),
                              imageHolder);
        imageHolder.setIsSelected(true);
    }
    
    /**
     * Save a memento of image
     * @param imageHolder 
     */
    public void saveToMemento(ImageHolder<T, U> imageHolder)
    {
        imageHolder.getOriginatorImage().getState().getSavedStatesImage()
                .addMemento(imageHolder.getOriginatorImage().saveToMemento());
    }
    
    /**
     * Restore the state of image with a memento
     * @param imageHolder
     * @return 
     */
    public boolean restoreLastMemento(ImageHolder<T, U> imageHolder)
    {
        if (!imageHolder.getOriginatorImage().getState()
                .getFilters().isEmpty())
        {
            try
            {
                if (imageHolder.getOriginatorImage().getState()
                        .getSavedStatesImage().isEmpty())
                    throw new RestoreMementoException();
                imageHolder.getOriginatorImage()
                            .restoreFromMemento(imageHolder
                                                .getOriginatorImage()
                                                .getState()
                                                .getSavedStatesImage()
                                                .getMemento(imageHolder
                                                    .getOriginatorImage()
                                                    .getState()
                                                    .getSavedStatesImage()
                                                    .getSavedStatesSize() - 1));
                return true;
            }
            catch (final RestoreMementoException e) { }
        }
        return false;
    }
    /**
     * Deselect the current image opened by user
     */
    public void deselectCurrentImageHolder()
    {
        ImageHolder<T, U>   imageHolder = getImageHolderSelected();
        
        if (imageHolder != null)
            imageHolder.setIsSelected(false);
    }
}
