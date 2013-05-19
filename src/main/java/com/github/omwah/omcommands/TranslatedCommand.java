package com.github.omwah.omcommands;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.CommandSender;

/**
 * A command that looks for a translation in a ResourceBundle
 */
public abstract class TranslatedCommand extends BasicCommand {
    private final ResourceBundle translation;
    
    /*
     * Creates translated command using default translation for 
     * OmCommands, which will not include the description and usage
     * for extending classes.
     */
    public TranslatedCommand(String name) {
        this(name, ResourceBundle.getBundle("com.github.omwah.omcommands.DefaultTranslation"));
    }
    
    /*
     * Creates a translated command using the supplied translation.
     * Automatically picks up the description and usage from the translation
     * based on the name of the class.
     */
    public TranslatedCommand(String name, ResourceBundle translation) {
        super(name);
        
        this.translation = translation;
        
        // Log missing description and usage instead of failing
        // Not a fatal, but a severe problem. But rest of program
        // can still operate
        Logger log = Logger.getLogger("Minecraft");
     
        // Set description from translation
        String desc_key = this.getClass().getSimpleName() + "-description";
        try {
            setDescription(getTranslation(desc_key));
        } catch (MissingResourceException ex) {
            log.log(Level.SEVERE, "[OmCommands] Could not find translation for: {0}", desc_key);
        }
        
        // Set usage from translation
        String usage_key = this.getClass().getSimpleName() + "-usage";
        try {
            setUsage(getTranslation(usage_key));
        } catch (MissingResourceException ex) {
            log.log(Level.SEVERE, "[OmCommands] Could not find translation for: {0}", usage_key);
        }
    }
        
    @Override
    public void displayHelp(String label, CommandSender sender) {
        
        sender.sendMessage(getTranslation("TranslatedCommand-help_line_command",
                this.getName()));

        sender.sendMessage(getTranslation("TranslatedCommand-help_line_description",
                this.getDescription()));
        
        sender.sendMessage(getTranslation("TranslatedCommand-help_line_usage",
                this.getUsage(label)));
    }
    
    /*
     * Translates a message with the supplied arguments
     */
    
    public String getTranslation(String key, Object... arguments) {
        return MessageFormat.format(translation.getString(key), arguments);
    }
    
    /*
     * Translates a message
     */
    public String getTranslation(String key) {
        return translation.getString(key);
    }
    
}
