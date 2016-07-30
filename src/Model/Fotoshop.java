package Model;

import Exception.InvalidInputException;
import Exception.CommandNotImplementedException;
import Exception.InvalidWordException;
import Helper.OPHelper;
import Model.Command.CommandWordsFotoshop;
import Model.Manager.ImageManager;
import Model.Command.RequestCommand;
import java.awt.image.BufferedImage;

/**
 * Fotoshop class, the main class of the application
 * @author Fabien PHAM
 * @param <T>
 * @param <U>
 */
public final class Fotoshop<T, U extends BufferedImage> extends AEditor
{
    private final ImageManager<T, U>   imageManager;
    
    /**
     * Initialize the fotoshop class
     */
    public Fotoshop()
    {
        super(new CommandWordsFotoshop());
        this.imageManager = new ImageManager<>();
    }
 
    /**
     * Edit the right action which is bounded with the input
     * @param input
     */
    @Override
    public void edit(String input)
    {
        TextInputControlFotoshop    textInputControlFotoshop = 
                new TextInputControlFotoshop(this);
        boolean                     retValue = false;
        
        try
        {
            textInputControlFotoshop.inputExists(input);
            this.parser.inputToWordsList(input.trim());
            
            RequestCommand  requestCommand = this.parser.getWords();
            
            textInputControlFotoshop.wordExists(requestCommand);
            this.commandExecutor
                    .setCommand(commandFactory.getDependency(this.commandWords,
                                                            requestCommand.getWord(0)));
            textInputControlFotoshop.commandExists(requestCommand);
            retValue = this.commandExecutor.executeCommand(this, requestCommand);
        }
        catch (final InvalidInputException
                | InvalidWordException
                | CommandNotImplementedException e)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(e.getMessage())
                    .appendMessage(System.lineSeparator());
        }
        setChanged();
        notifyObservers(retValue);
        this.parser.clearWordsList();
    }
    
    /**
     * Get the image's manager
     * @return
     */
    public ImageManager<T, U> getImageManager() { return this.imageManager; }
}
