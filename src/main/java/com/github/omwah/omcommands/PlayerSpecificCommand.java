package com.github.omwah.omcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/*
 * Simply adds helper functionality for commands that deal with specific players
 */
public abstract class PlayerSpecificCommand extends BasicCommand
{
    public PlayerSpecificCommand(String name) {
        super(name);
    }

    /*
     * Gets a player name either as the Sender if it is a Player ir from the arguments
     * if it is supplied there. Returns null if there is a problem, which will be reported
     * to the sender.
     */
    protected String getDestPlayer(CommandHandler handler, CommandSender sender, String[] args, int playerIndex) {

        // Make sure we are at console or sender has sufficient privileges
        // if a owner name is specified
        String player_name = null;
        if (playerIndex >= 0 && args.length > playerIndex && handler.hasAdminPermission(sender)) {
            
            // Op or admin creating a bank account for another player
            player_name = args[playerIndex];
            
        } else if (sender instanceof Player) {
            // Player is sender, make sure they only try and access their own account when a player name
            // is specified
            //
            Player player_obj = (Player) sender;
            if(playerIndex >= 0 && args.length > playerIndex && !args[playerIndex].equalsIgnoreCase(player_obj.getName())) {
                // Player tried to specify owner's name without sufficient privileges
                sender.sendMessage("Insufficient privileges to access another player's account");
                return null;

            } else {
                // Be extra careful that we don't entirely trust the string they sent
                player_name = ((Player) sender).getName();
            }

        } else {
            // Sender is not a Player so must be a console access
            sender.sendMessage("Must specify player name when using this command from the console");
            return null;
        }

        return player_name;
     }
}
