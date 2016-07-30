package Exception;

import java.io.FileNotFoundException;

/**
 * Custom exception for image not found for fotoshop application 
 * @author Fabien PHAM
 */
public class ImageNotFoundException extends FileNotFoundException
{

    /**
     * Initialize exception
     * @param s
     */
    public ImageNotFoundException(String s) { super(s); }
}
