package Model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import static java.awt.image.BufferedImage.TYPE_INT_RGB;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Fabien PHAM
 */
public class ColorImageTest
{   
    public ColorImageTest() { }
    
    @BeforeClass
    public static void setUpClass() { }
    
    @AfterClass
    public static void tearDownClass() { }
    @Before
    public void setUp() { }
    
    @After
    public void tearDown() { }

    /**
     * Test of getNewInstance method with positive width/height, of class ColorImage.
     */
    @Test
    public void testGetNewInstance_BufferedImage_positive_width_height()
    {
        int             width = 10;
        int             height = 10;
        BufferedImage   image = new BufferedImage(width, height, TYPE_INT_RGB);
        ColorImage      result = ColorImage.getNewInstance(image);
        
        assertTrue(result.getWidth() == width);
        assertTrue(result.getHeight() == height);
    }
    
    /**
     * Test of getNewInstance method with negative width/height, of class ColorImage.
     */
    @Test
    public void testGetNewInstance_BufferedImage_negative_width_height()
    {
        int             width = -10;
        int             height = -10;
        BufferedImage   image = new BufferedImage(width, height, TYPE_INT_RGB);
        ColorImage      result = ColorImage.getNewInstance(image);
        
        assertFalse(result.getWidth() == width);
        assertFalse(result.getHeight() == height);
    }
    
    /**
     * Test of getNewInstance method with positive parameters, of class ColorImage.
     */
    @Test
    public void testGetNewInstance_int_int_positive_width_height()
    {
        int             width = 10;
        int             height = 10;
        
        ColorImage      result = ColorImage.getNewInstance(width, height);
        
        assertTrue(result.getWidth() == width);
        assertTrue(result.getHeight() == height);
    }

    /**
     * Test of getNewInstance method with negative parameters, of class ColorImage.
     */
    @Test
    public void testGetNewInstance_int_int_negative_width_height()
    {
        int             width = -10;
        int             height = -10;   
        ColorImage      result = ColorImage.getNewInstance(width, height);
        
        assertFalse(result.getWidth() == width);
        assertFalse(result.getHeight() == height);
    }
    
    /**
     * Test of clone method, of class ColorImage.
     * @throws java.lang.Exception
     */
    @Test
    public void testClone()
            throws Exception
    {
        int         width = 10;
        int         height = 10;
        ColorImage  instance = ColorImage.getNewInstance(width, height);
        ColorImage  result = (ColorImage)instance.clone();
       
        assertTrue(result.getWidth() == instance.getWidth());
        assertTrue(result.getHeight() == instance.getHeight());
    }

    /**
     * Test of setPixel method with positive parameters, of class ColorImage.
     */
    @Test
    public void testSetPixel_positive_x_y()
    {
        int         width = 10;
        int         height = 10;
        int         x = 5;
        int         y = 5;
        Color       col = new Color(10, 10, 10);
        ColorImage  result = ColorImage.getNewInstance(width, height);
        
        result.setPixel(x, y, col);
        assertTrue(result.getRGB(x, y) == col.getRGB());
    }
    
    /**
     * Test of setPixel method with positive parameters exceeding width/height,
     * of class ColorImage.
     */
    @Test
    public void testSetPixel_positive_x_y_exceeding_width_height()
    {
        int         width = 10;
        int         height = 10;
        int         x = 15;
        int         y = 15;
        Color       col = new Color(10, 10, 10);
        ColorImage  result = ColorImage.getNewInstance(width, height);
        
        result.setPixel(x, y, col);
        assertTrue(result.getRGB(x, y) == col.getRGB());
    }
    /**
     * Test of setPixel method with negative parameters, of class ColorImage.
     */
    @Test
    public void testSetPixel_negative_x_y()
    {
        int         width = 10;
        int         height = 10;
        int         x = -5;
        int         y = -5;
        Color       col = new Color(10, 10, 10);
        ColorImage  result = ColorImage.getNewInstance(width, height);
        
        result.setPixel(x, y, col);
        assertFalse(result.getRGB(x, y) == col.getRGB());
    }
    
    /**
     * Test of getPixel method with positive parameters, of class ColorImage.
     */
    @Test
    public void testGetPixel_positive_x_y()
    {
        int         width = 10;
        int         height = 10;
        int         x = 5;
        int         y = 5;
        Color       instance = new Color(10, 10, 10);
        ColorImage  colorImage = ColorImage.getNewInstance(width, height);
        Color       result;
        
        colorImage.setPixel(x, y, instance);
        result = colorImage.getPixel(x, y);
        assertTrue(result.getRGB() == instance.getRGB());
    }
    
    /**
     * Test of getPixel method with positive parameters exceeding width/height,
     * of class ColorImage.
     */
    @Test
    public void testGetPixel_positive_x_y_exceeding_width_height()
    {
        int         width = 10;
        int         height = 10;
        int         x = 15;
        int         y = 15;
        Color       instance = new Color(10, 10, 10);
        ColorImage  colorImage = ColorImage.getNewInstance(width, height);
        Color       result;
        
        colorImage.setPixel(x, y, instance);
        result = colorImage.getPixel(x, y);
        assertTrue(result.getRGB() == instance.getRGB());
    }
    
    /**
     * Test of getPixel method with negative parameters, of class ColorImage.
     */
    @Test
    public void testGetPixel_negative_x_y()
    {
        int         width = 10;
        int         height = 10;
        int         x = -5;
        int         y = -5;
        Color       instance = new Color(10, 10, 10);
        ColorImage  colorImage = ColorImage.getNewInstance(width, height);
        Color       result;
        
        colorImage.setPixel(x, y, instance);
        result = colorImage.getPixel(x, y);
        assertFalse(result.getRGB() == instance.getRGB());
    }
}
