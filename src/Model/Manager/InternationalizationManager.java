package Model.Manager;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class managing internationalization
 * @author Fabien PHAM
 */
public final class InternationalizationManager
{
    /**
     * Enumeration for choose the right language for the application
     */
    public enum Location
    {
        ENGLISH("en", "GB");
        
        private final String  language;
        private final String  country;
        
        private Location(String language, String country)
        {
            this.language = language;
            this.country = country;
        }
        
        public String getLanguage() { return this.language; }
        public String getCountry() { return this.country; }
        
        /**
         * Function for check if the location exists depending on the language
         * and the country received by parameters
         * @param language
         * @param country
         * @return 
         */
        public static Location getLocation(String language, String country)
        {
            for (Location l : Location.values())
            {
                if (l.getLanguage().equals(language)
                        && l.getCountry().equals(country))
                    return l;
            }
            return null;
        }
    }
    
    /**
     * Default basename for allow instance of ResourceBundle
     */
    private static final String BASENAME = "resource.MessagesBundle";
    
    /**
     * Singleton instance of InternationalizationManager
     */
    private static InternationalizationManager  instance;
    
    /**
     * Object containing sentences depending on the language chosen
     */
    private final ResourceBundle    resourceBundle;
    
    /**
     * Location enumeration containing language and country strings
     */
    private final Location          location;
    /**
     * Initialize the InternationalizationManager by initializing
     * Locale and ResourceBundle classes by default
     */
    private InternationalizationManager()
    {
        this.resourceBundle = ResourceBundle.getBundle(BASENAME);
        this.location = Location.ENGLISH;
    }
    
    /**
     * Initialize the InternationalizationManager by initializing
     * Locale and ResourceBundle depending on the language and the country
     * received by parameter
     * @param language
     * @param country 
     */
    private InternationalizationManager(String language, String country)
    {
        Location    location = Location.getLocation(language, country);
        
        if (location != null)
        {
            this.resourceBundle = ResourceBundle
                    .getBundle(BASENAME, new Locale(language, country));
            this.location = location;
        }
        else
        {
            this.resourceBundle = ResourceBundle.getBundle(BASENAME);
            this.location = Location.ENGLISH;
        }
    }
    
    /**
     * Get the single instance of InternationalizationManager with
     * calling the default constructor
     * @return 
     */
    public static InternationalizationManager getInstance()
    {
        if (instance == null)
            instance = new InternationalizationManager();
        return instance;
    }
    
    /**
     * Get the single instance of InternationalizationManager with
     * calling the constructor depending on the language and the country chosen
     * @param language
     * @param country
     * @return 
     */
    public static InternationalizationManager getInstance(String language,
                                                          String country)
    {
        if (instance == null)
            instance = new InternationalizationManager(language, country);
        return null;
    }
    
    /**
     * Get the Location enumeration
     * @return 
     */
    public Location getLocation() { return this.location; }
    
    /**
     * Call getString method from ResourceBundle class
     * @param key
     * @return 
     */
    public String getStringFromResourceBundle(String key)
    {
        return this.resourceBundle.getString(key);
    }
}
