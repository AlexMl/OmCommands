package com.github.omwah.omcommands;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.command.CommandSender;

public abstract class BasicCommand implements PluginCommand {

    protected String name;
    protected String description = "";
    protected String usage = "";
    protected String permission = "";
    protected ArrayList<String> identifiers;
    protected int minArguments = 0;
    protected int maxArguments = 0;

    public BasicCommand(String name) {
        this.name = name;
        this.identifiers = new ArrayList<String>();
    }

    public void cancelInteraction(CommandSender executor) {}

    public String getDescription() {
        return description;
    }

    public String[] getIdentifiers() {
        return (String[]) identifiers.toArray();
    }

    public int getMaxArguments() {
        return maxArguments;
    }

    public int getMinArguments() {
        return minArguments;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public String getUsage(String label) {
        if (this.identifiers.contains(label.toLowerCase()) ){
            // Queried as a main command
            return MessageFormat.format(usage, label);
        } else {
            // Queried as a sub command
            return MessageFormat.format(usage, label + " " + getName());
        }
    }
    
    public void displayHelp(String label, CommandSender sender) {
        sender.sendMessage("§cCommand:§e " + this.getName());
        sender.sendMessage("§cDescription:§e " + this.getDescription());
        sender.sendMessage("§cUsage:§e " + this.getUsage(label));
    }
    
    public boolean isIdentifier(CommandSender executor, String input) {
        for (String identifier : identifiers) {
            if (input.equalsIgnoreCase(identifier)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInProgress(CommandSender executor) {
        return false;
    }

    public boolean isInteractive() {
        return false;
    }

    public boolean isShownOnHelpMenu() {
        return true;
    }

    public void setArgumentRange(int min, int max) {
        this.minArguments = min;
        this.maxArguments = max;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdentifiers(String... identifiers) {
        this.identifiers.addAll(Arrays.asList(identifiers));
    }
    
    public void addIdentifier(String new_identifier) {
        this.identifiers.add(new_identifier);
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

}
