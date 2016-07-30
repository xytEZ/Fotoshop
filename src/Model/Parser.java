package Model;

import Model.Command.RequestCommand;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parser for cut the command, arguments of input and store it in a
 * RequestCommand class
 * @author  Fabien PHAM
 */
public final class Parser 
{
    private static final String PATTERN = "\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?";
    
    private final List<String>  words;
    
    public Parser() { this.words = new ArrayList<>(); }
    
    /**
     * Build a RequestCommand class from the input
     * @param input
     */
    public void inputToWordsList(String input)
    {
        this.words.addAll(Arrays.asList(input.replaceAll("^\"", "").split(PATTERN)));
    }
    
    public RequestCommand getWords() { return new RequestCommand(words); }
    public void clearWordsList() { this.words.clear(); }
}
