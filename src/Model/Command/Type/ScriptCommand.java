package Model.Command.Type;

import Helper.OPHelper;
import Model.AModel;
import Model.Command.ICommand;
import Model.Command.RequestCommand;
import Exception.CommandNotImplementedException;
import Exception.InvalidInputException;
import Exception.InvalidWordException;
import Model.TextInputControlFotoshop;
import Model.Fotoshop;
import Model.Manager.InternationalizationManager;
import Model.Parser;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Script command
 * @author Fabien PHAM
 */
public class ScriptCommand implements ICommand
{
    /**
     * Perform the lecture of commands since a source file and execute them
     * @param fotoshop
     * @param requestCommand
     * @return 
     */
    private boolean performScriptOnFile(final Fotoshop fotoshop,
                                        RequestCommand requestCommand)
    {
        
        String  scriptName = requestCommand.getWord(1);
        
        try (FileInputStream inputStream = new FileInputStream(scriptName))
        {
            TextInputControlFotoshop    textInputControlFotoshop = 
                    new TextInputControlFotoshop(fotoshop);
            Parser                      parser = new Parser();
            Scanner                     scanner = new Scanner(inputStream);
            boolean                     finished = false;
            
            while (!finished)
            {
                try
                {
                    String  input = scanner.nextLine();
                    
                    textInputControlFotoshop.inputExists(input);
                    parser.inputToWordsList(input.trim());
                    
                    RequestCommand  requestCommand2 = parser.getWords();
                    
                    textInputControlFotoshop.wordExists(requestCommand);
                    fotoshop.getCommandExecutor()
                        .setCommand(fotoshop.getCommandFactory()
                                    .getDependency(fotoshop.getCommandWords(),
                                                    requestCommand2.getWord(0)));
                    textInputControlFotoshop.commandExists(requestCommand2);
                    finished = fotoshop.getCommandExecutor()
                            .executeCommand(fotoshop, requestCommand2);
                }
                catch (final InvalidInputException
                        | InvalidWordException
                        | CommandNotImplementedException e)
                {
                    OPHelper.MessageHelper.getInstance()
                            .appendMessage(e.getMessage());
                }
                catch (final Exception e)
                {
                    return finished;
                }
                parser.clearWordsList();
                OPHelper.MessageHelper.getInstance()
                        .appendMessage(System.lineSeparator());
            }
            return finished;
        }
        catch (final FileNotFoundException e)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage("")
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("script_command_cannot_find"))
                    .appendMessage(" \"")
                    .appendMessage(scriptName)
                    .appendMessage("\"")
                    .appendMessage(System.lineSeparator());
            return false;
        }
        catch (final IOException e)
        {
            throw new RuntimeException("Panic: script barfed!");
        }
    }
    
    /**
     * Execute the Script command
     * @param model
     * @param requestCommand
     * @return
     */
    @Override
    public boolean execute(final AModel model,
                            RequestCommand requestCommand)
    {
        final Fotoshop  fotoshop = (Fotoshop)model;
        
        if (requestCommand.getWord(1) == null)
        {
            OPHelper.MessageHelper.getInstance()
                    .appendMessage(InternationalizationManager.getInstance()
                            .getStringFromResourceBundle("script_command_which_script"))
                    .appendMessage(System.lineSeparator());
            return false;
        }
        return performScriptOnFile(fotoshop, requestCommand);
    } 
}
