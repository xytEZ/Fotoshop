package Model;

import Exception.ImageNotFoundException;
import Model.Manager.FilterManager;
import Model.Manager.InternationalizationManager;
import Model.Memento.Caretaker;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Class containing the old states of the image and the filters applied
 * and the state of the image
 * @author Fabien PHAM
 * @param <T>
 * @param <U>
 */
public final class EditableImage<T, U extends BufferedImage> implements Cloneable
{
    /**
     * Object containing the saved of the old states of the image
     */
    private Caretaker<EditableImage<T, U>>    savedStatesImage;
    
    /**
     * Object containing the filters applied to the image
     */
    private FilterManager<T>          filters;
    
    /**
     * The state of the image
     */
    private U                         currentImage;
    
    /**
     * Name of the image
     */
    private String                    filename;
    
    /**
     * Initialize Image
     * @param currentImage
     * @param filename
     */
    public EditableImage(U currentImage, String filename)
    {
        this.savedStatesImage = new Caretaker<>();
        this.filters = new FilterManager<>();
        this.currentImage = currentImage;
        this.filename = filename;
    }
    
    /**
     * Clone the current instance by deep copy
     * @return 
     */
    @Override
    public Object clone()
    {
        EditableImage<T, U> image = null;
        
        try
        {
            image = (EditableImage<T, U>)super.clone();
            image.savedStatesImage = (Caretaker<EditableImage<T, U>>) this.savedStatesImage.clone();
            image.filters = (FilterManager<T>) this.filters.clone();
        }
        catch (final CloneNotSupportedException e)
        {
            throw new RuntimeException(e.getCause());
        }
        return image;
    }
    
    /**
     * return a error message for FileNotFoundException during attempt to
     * load image
     * @param inputName
     * @param s
     * @return 
     */
    private static StringBuilder buildMsgError(String inputName, String s)
    {
        StringBuilder   errorMessage = new StringBuilder();

        errorMessage.append(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("cannot"))
                    .append(" ")
                    .append(s)
                    .append(" ")
                    .append(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("image_file"))
                    .append(", \"")
                    .append(inputName)
                    .append("\"")
                    .append(System.lineSeparator())
                    .append(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("cwd_is"))
                    .append(" \"")
                    .append(System.getProperty("user.dir"))
                    .append("\"");
        return errorMessage;
    }
    
    /**
     *
     * Load a new image
     * @param <U>
     * @param inputName
     * @return
     * @throws ImageNotFoundException
     */
    public static <U> U loadImage(String inputName)
            throws ImageNotFoundException
    {
        U   img = null;

        try 
        {
            File    inputFile = new File(inputName);
            
            if (!inputFile.exists())
                throw new FileNotFoundException();
            if (inputFile.isDirectory()
                    || !inputFile.canRead())
                throw new IOException();
            
            BufferedImage   tmpImage = ImageIO.read(inputFile);

            if (tmpImage == null)
                throw new IOException();
            img = (U)ColorImage.getNewInstance(tmpImage);
                                                    
        }
        catch (final FileNotFoundException e)
        {
            throw new ImageNotFoundException(buildMsgError(inputName, 
                    InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("find"))
                                            .toString());
        }
        catch (final IOException e)
        {
            throw new ImageNotFoundException(buildMsgError(inputName,
                    InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("load"))
                                            .toString());
        }
        return img;
    }
    
    /**
     * Get the filters
     * @return 
     */
    public FilterManager<T> getFilters() { return this.filters; }
 
    /**
     * Get the current image
     * @return
     */
    public U getCurrentImage() { return this.currentImage; }
    
    /**
     * Set the current image
     * @param currentImage
     */
    public void setCurrentImage(U currentImage) { this.currentImage = currentImage; }
    
    /**
     * Get the name of image
     * @return 
     */
    public String getFilename() { return this.filename; }
    
    /**
     * Set the name of image
     * @param filename 
     */
    public void setFilename(String filename) { this.filename = filename; }
    
    /**
     * Get the old states of the image
     * @return 
     */
    public Caretaker getSavedStatesImage() { return this.savedStatesImage; }
}