package me.goodestenglish.streamer.command.impl;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.command.Command;
import me.goodestenglish.streamer.util.CC;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class SetHostCommand extends Command {
    @Override
    public String getCommandName() {
        return "sethost";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length != 1) {
            sender.addChatMessage(new ChatComponentText(CC.RED + "指令用法: /sethost <資料庫IP>"));
            return;
        }
        Streamer.INSTANCE.getConfig().setMongoHost(args[0]);
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.GREEN + "成功設置你的資料庫IP! " + CC.YELLOW + "正在嘗試重新登入資料庫..."));
        Streamer.INSTANCE.getMongoDB().connect().whenComplete((aBoolean, throwable) -> {
            if (throwable != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.RED + "錯誤出現了! " + CC.GRAY + "基於安全原因, 請到log檔案查看錯誤"));
                return;
            }
            if (aBoolean) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.GREEN + "成功登入資料庫!"));
            } else {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.GREEN + "無法登入資料庫! 請檢查IP是否正確"));
            }
        });
    }
}
