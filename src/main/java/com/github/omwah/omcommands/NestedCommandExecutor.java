package com.github.omwah.omcommands;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * CommandExectuor that dispatches commands to CommandHandler classes and supports
 * nested sub commands
 */
public abstract class NestedCommandExecutor implements CommandExecutor {
    private final CommandHandler commandHandler;
    private final ResourceBundle translation;

    /*
     * Set up CommandExecutor with a default translation
     */
    public NestedCommandExecutor(JavaPlugin plugin, Command cmd, String admin_permission) {
        this(plugin, cmd, admin_permission, 
            ResourceBundle.getBundle("com.github.omwah.omcommands.DefaultTranslation"));
    }
    
    /*
     * Set up CommandExecutor with a specific translation
     */
    public NestedCommandExecutor(JavaPlugin plugin, Command cmd, String admin_permission, ResourceBundle translation) {
        // Translation to use
        this.translation = translation;
        
        // Set up sub commands
        List<PluginCommand> sub_cmd_list = getSubCommands(plugin);
        
        // Use LinkedHashMap so values are in the order they were inserted
        Map<String, PluginCommand> sub_cmd_map = new LinkedHashMap<String, PluginCommand>();
        for (PluginCommand sub_cmd : sub_cmd_list) {
            sub_cmd_map.put(sub_cmd.getName(), sub_cmd);
        }

        this.commandHandler = new CommandHandler(admin_permission);
        if (sub_cmd_map.containsKey(cmd.getName())) {
            // Set up the command handler with the sole sub command
            // that has been promoted to a top level command
            this.commandHandler.addCommand((PluginCommand) sub_cmd_map.get(cmd.getName()));
        } else {
            // Set up sub commands under a plugin top level command with a help command
            
            // Add help commmand along with aliases to make it respond to command and
            // its aliases when no arguments are supplied
            HelpCommand help_cmd = new HelpCommand(plugin.getName(), getTranslation());
            help_cmd.addIdentifier(cmd.getName());
            for (Iterator alias_iter = cmd.getAliases().iterator(); alias_iter.hasNext();) {
                help_cmd.addIdentifier((String) alias_iter.next());
            }             
            
            this.commandHandler.addCommand(help_cmd);
            for (Iterator sub_cmd_iter = sub_cmd_map.values().iterator(); sub_cmd_iter.hasNext();) {
                this.commandHandler.addCommand((PluginCommand) sub_cmd_iter.next());
            }
        }
    }

    /*
     * Helper routine for defining and instantiating sub commands
     * Which may or may not be promoted to top level commands
     * based on what is present in plugin.yml
     */

    protected abstract List<PluginCommand> getSubCommands(JavaPlugin plugin);
  

    /*
     * Dispatch commands through CommandHandler 
     */
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return commandHandler.dispatch(sender, command, label, args);
    }

    /*
     * Returns the command handler
     */

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }
    
    /*
     * Get translation class
     */
    
    public ResourceBundle getTranslation() {
        return translation;
    }

}
