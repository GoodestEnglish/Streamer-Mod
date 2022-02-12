package me.goodestenglish.streamer.util;

import me.goodestenglish.streamer.event.PlayerConnectedToServerEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IWorldAccess;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventDispatcher {
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    @SubscribeEvent
    public void onClientConnectedToServer(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        Minecraft minecraft = Minecraft.getMinecraft();
        EntityPlayerSP player = minecraft.thePlayer;

        if (player == null) {
            executor.submit(() -> {
                while (minecraft.thePlayer == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return;
                    }
                }

                MinecraftForge.EVENT_BUS.post(new PlayerConnectedToServerEvent(minecraft.thePlayer));
            });
        } else {
            MinecraftForge.EVENT_BUS.post(new PlayerConnectedToServerEvent(player));
        }
    }

    public static class WorldAccess implements IWorldAccess {
        @Override
        public void markBlockForUpdate(BlockPos pos) {
        }

        @Override
        public void notifyLightSet(BlockPos pos) {
        }

        @Override
        public void markBlockRangeForRenderUpdate(int x1, int y1, int z1, int x2, int y2, int z2) {
        }

        @Override
        public void playSound(String soundName, double x, double y, double z, float volume, float pitch) {
        }

        @Override
        public void playSoundToNearExcept(EntityPlayer except, String soundName, double x, double y, double z, float volume, float pitch) {
        }

        @Override
        public void spawnParticle(int particleID, boolean ignoreRange, double xCoord, double yCoord, double zCoord, double xOffset, double yOffset, double zOffset, int... parameters) {
        }

        @Override
        public void onEntityAdded(Entity entityIn) {

        }

        @Override
        public void onEntityRemoved(Entity entityIn) {
        }

        @Override
        public void playRecord(String recordName, BlockPos blockPosIn) {
        }

        @Override
        public void broadcastSound(int soundID, BlockPos pos, int data) {
        }

        @Override
        public void playAuxSFX(EntityPlayer player, int sfxType, BlockPos blockPosIn, int data) {
        }

        @Override
        public void sendBlockBreakProgress(int breakerId, BlockPos pos, int progress) {
        }
    }
}
