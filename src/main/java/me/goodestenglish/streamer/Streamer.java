package me.goodestenglish.streamer;

import lombok.Getter;
import me.goodestenglish.streamer.command.impl.ClearWaitlistCommand;
import me.goodestenglish.streamer.command.impl.SetHostCommand;
import me.goodestenglish.streamer.command.impl.StreamerCommand;
import me.goodestenglish.streamer.command.impl.TestCommand;
import me.goodestenglish.streamer.database.MongoDB;
import me.goodestenglish.streamer.listener.JoinListener;
import me.goodestenglish.streamer.util.CC;
import me.goodestenglish.streamer.util.EventDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.bson.Document;

import java.util.concurrent.CompletableFuture;

@Mod(modid = "streamer", name = "Streamer", version = Streamer.VERSION, clientSideOnly = true)
public class Streamer {
    public static final String VERSION = "1.0";

    public static Streamer INSTANCE;
    @Getter private StreamerConfig config;
    @Getter private MongoDB mongoDB;

    @Mod.EventHandler
    public void onPreInit(final FMLPreInitializationEvent event) {
        INSTANCE = this;
        config = new StreamerConfig(event.getSuggestedConfigurationFile());
        mongoDB = new MongoDB();
        registerCommands();
        registerListener();
    }

    public void registerListener() {
        MinecraftForge.EVENT_BUS.register(new EventDispatcher());
        MinecraftForge.EVENT_BUS.register(new JoinListener());
    }

    public void registerCommands() {
        new StreamerCommand().register();
        new ClearWaitlistCommand().register();
        new SetHostCommand().register();

        new TestCommand().register();
    }

    public void clearWaitingList() {
        CompletableFuture.runAsync(() -> {
            long count = Streamer.INSTANCE.getMongoDB().getMongoCollection().countDocuments();
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.YELLOW + "正在清除等候名單... " + CC.GRAY + "(合共 " + CC.GOLD + CC.BOLD + count + CC.GRAY + " 位)"));
            Streamer.INSTANCE.getMongoDB().getMongoCollection().deleteMany(new Document());
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.GREEN + "成功清除等候名單!"));
        });
    }
}
