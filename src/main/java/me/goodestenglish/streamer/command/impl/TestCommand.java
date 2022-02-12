package me.goodestenglish.streamer.command.impl;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.command.Command;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import org.bson.Document;

import java.util.concurrent.ThreadLocalRandom;

public class TestCommand extends Command {
    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        String prefix = "streamermodtest";
        for (int i = 0; i < 10; i++) {
            Document document = new Document();
            document.put("username", prefix+ i);
            document.put("discord_id", String.valueOf(i));
            document.put("save_time", ThreadLocalRandom.current().nextLong(0,99999));

            Streamer.INSTANCE.getMongoDB().replaceResult(prefix+i, document);
        }

    }
}
