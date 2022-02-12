package me.goodestenglish.streamer.event;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.eventhandler.Event;

public class PlayerConnectedToServerEvent extends Event {
    private EntityPlayerSP player;

    public PlayerConnectedToServerEvent(EntityPlayerSP player) {
        this.player = player;
    }

    public EntityPlayerSP getPlayer() {
        return player;
    }
}