package me.goodestenglish.streamer.listener;

import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.event.PlayerConnectedToServerEvent;
import me.goodestenglish.streamer.util.CC;
import net.minecraft.client.Minecraft;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class JoinListener {

    @SubscribeEvent
    public void onPlayerJoinedServer(PlayerConnectedToServerEvent event) {
        if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel.net")) {
            if (!Streamer.INSTANCE.getMongoDB().isEnabled()) {
                event.getPlayer().addChatMessage(new ChatComponentText(CC.RED + "無法連接到 MongoDB! 觀眾場指令將無法使用!"));
            } else {
                event.getPlayer().addChatMessage(new ChatComponentText(CC.GREEN + "觀眾場指令隨時可以使用!"));
                event.getPlayer().addChatMessage(new ChatComponentText(CC.YELLOW + "[點我清除等候名單]")
                        .setChatStyle(new ChatStyle().setChatHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(CC.GRAY + "輕輕點我 >_<")))
                                .setChatClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clearwaitlist"))));
            }
        }
    }

}
