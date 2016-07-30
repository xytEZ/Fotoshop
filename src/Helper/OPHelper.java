package Helper;

/**
 * Namespace for group all operations class using everywhere in code
 * @author Fabien PHAM
 */
public final class OPHelper
{

    /**
     * Class using singleton pattern for get back message intended
     * for display
     */
    public static final class MessageHelper
    {
        /**
         * Single instance of MessageHelper
         */
        private static MessageHelper    instance;
        
        /**
         * Object containing message intended for display
         */
        private final StringBuilder     message;
        
        /**
         * Initialize the MessageHelper
         */
        private MessageHelper() { this.message = new StringBuilder(); }
        
        /**
         * Get the single instance of MessageHelper
         * @return
         */
        public static MessageHelper getInstance()
        {
            if (instance == null)
                instance = new MessageHelper();
            return instance;
        }
        
        /**
         * Concatenate message existing with new string
         * @param s
         * @return
         */
        public MessageHelper appendMessage(String s)
        {
            this.message.append(s);
            return this;
        }

        /**
         * Release the message and set to empty the message
         */
        public void clearMessage() { this.message.setLength(0); }

        /**
         * Go out the string message by string format
         * @return 
         */
        @Override
        public String toString() { return this.message.toString(); }
    }
    
    /**
     * Constructor private because OPHelper is transformed to namespace for 
     * allow readability of code when using MessageHelper 
     * or WrapperResourceBundle class
     * 
     */
    private OPHelper()
    {
    }
}
