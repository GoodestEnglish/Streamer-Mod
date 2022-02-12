package me.goodestenglish.streamer.command.impl;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.command.Command;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;

public class ClearWaitlistCommand extends Command {
    @Override
    public String getCommandName() {
        return "clearwaitlist";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Streamer.INSTANCE.clearWaitingList();
    }
}
