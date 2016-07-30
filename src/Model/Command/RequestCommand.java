package Model.Command;

import java.util.List;

/**
 * Class containing the input and separate by elements of a list
 * @author Fabien PHAM
 */
public class RequestCommand
{
    /**
     * List containing each word
     */
    private final List<String>    words;
    
    /**
     * Initialize the RequestCommand
     * @param words
     */
    public RequestCommand(List<String> words)
    {
        this.words = words;
    }

    /**
     * Get the word at indicated position
     * @param index
     * @return
     */
    public String getWord(int index)
    {
        return (index < this.words.size()) ? this.words.get(index) : null;
    }
}