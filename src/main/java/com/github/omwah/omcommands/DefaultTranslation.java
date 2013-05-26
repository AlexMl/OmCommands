package com.github.omwah.omcommands;

import java.util.ListResourceBundle;

/*
 * Default bare minimal translations needed for OmCommands to be usable
 * without needing ResourceBundles in the base class
 */

public class DefaultTranslation extends ListResourceBundle {
    
    private Object[][] contents = {
        { "CommandHandler-no_permission", "Insufficient permission"},
        { "TranslatedCommand-help_line_command", "§cCommand:§e {0}"},
        { "TranslatedCommand-help_line_description", "§cDescription:§e {0}"},
        { "TranslatedCommand-help_line_usage", "§cUsage:§e {0}"},
        { "PlayerSpecificCommand-no_permission", "Insufficient privileges to access another player's account"},
        { "PlayerSpecificCommand-specify_player_name", "Must specify player name when using this command from the console"},    
        { "HelpCommand-description", "Displays the help menu" },
        { "HelpCommand-usage", "/{0} help §8[page#]" },
        { "HelpCommand-banner", "§c-----[§f {0} Help <{1,number,integer}/{2,number,integer}>§c ]-----" },
        { "HelpCommand-help_line", "  §a{0}" },
        { "HelpCommand-for_more_help", "§cFor more info on a particular command, type §f/{0} <command> ?" },
    };
    
    @Override
    protected Object[][] getContents() {
        return contents;
    }

}