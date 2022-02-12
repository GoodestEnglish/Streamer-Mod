package me.goodestenglish.streamer.command.impl;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.command.Command;
import me.goodestenglish.streamer.util.CC;
import me.goodestenglish.streamer.util.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StreamerCommand extends Command {
    @Override
    public String getCommandName() {
        return "streamer";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(CC.RED + "指令用法: /streamer <人數>"));
            return;
        }

        if (!Util.isInteger(args[0])) {
            sender.addChatMessage(new ChatComponentText(CC.RED + "'" + args[0] + "' 並不是一個有效的數字!"));
            return;
        }

        sender.addChatMessage(new ChatComponentText(CC.YELLOW + "正在從資料庫獲取資料..."));

        int amount = Integer.parseInt(args[0]);
        if (amount > 5) {
            sender.addChatMessage(new ChatComponentText(CC.RED + "由於Hypixel 派對系統限制, 只能同時邀請五個玩家, 請重新輸入指令"));
            return;
        }
        Streamer.INSTANCE.getMongoDB().retrievePlayers().whenComplete((retrievedDocuments, throwable) -> {
            if (throwable != null) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.RED + "錯誤出現了! " + CC.GRAY + "基於安全原因, 請到log檔案查看錯誤"));
                return;
            }
            if (retrievedDocuments.isEmpty()) {
                sender.addChatMessage(new ChatComponentText(CC.RED + "沒有人正在等待觀眾場!"));
                return;
            }
            sender.addChatMessage(new ChatComponentText(CC.YELLOW + "現時有 " + CC.GOLD + CC.BOLD + retrievedDocuments.size() + CC.YELLOW + " 人正在等待... 根據先後次序, 選出最適合的觀眾進入派對"));
            retrievedDocuments.sort((o1, o2) -> (int) (o1.getSaveTime() - o2.getSaveTime()));
            List<String> results = new ArrayList<>();
            int count = 0;
            for (int i = 0; i < amount; i++) {
                if (retrievedDocuments.size() <= i) {
                    continue;
                }
                count++;
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/party " + retrievedDocuments.get(i).getUsername());
                results.add(CC.GRAY + "#" + (i+1) + " " + CC.YELLOW + retrievedDocuments.get(i).getUsername() + " " + CC.AQUA + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(retrievedDocuments.get(i).getSaveTime()));
                Streamer.INSTANCE.getMongoDB().removePlayerData(retrievedDocuments.get(i).getUsername());
            }

            sender.addChatMessage(new ChatComponentText(CC.GREEN + "成功邀請 " + CC.GOLD + CC.BOLD + count + CC.GREEN + " 人進入派對! ")
                    .appendSibling(new ChatComponentText(CC.YELLOW + "[總結一覽]")
                            .setChatStyle(new ChatStyle()
                                    .setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(String.join("\n", results)))))));
        });
    }
}
