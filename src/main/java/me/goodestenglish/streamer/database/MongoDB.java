package me.goodestenglish.streamer.database;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import lombok.Getter;
import me.goodestenglish.streamer.Streamer;
import me.goodestenglish.streamer.util.CC;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class MongoDB {

    @Getter private MongoClient mongoClient;
    @Getter private MongoDatabase mongoDatabase;
    @Getter private MongoCollection<Document> mongoCollection;
    @Getter private boolean isEnabled;

    public MongoDB() {
        connect();
    }

    public void removePlayerData(String username) {
        mongoCollection.findOneAndDelete(Filters.eq("username", username));
    }

    public void replaceResult(String username, Object document) {
        mongoCollection.replaceOne(Filters.eq("username", username), (Document) document, new ReplaceOptions().upsert(true));
    }

    public CompletableFuture<Boolean> connect() {
        /*try {
            if (SkyWarsCommon.INSTANCE.getConfigFile().getBoolean("MONGO.AUTHENTICATION.ENABLED")) {
                final MongoCredential credential = MongoCredential.createCredential(
                        SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.AUTHENTICATION.USERNAME"),
                        SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.AUTHENTICATION.DATABASE"),
                        SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.AUTHENTICATION.PASSWORD").toCharArray()
                );
                mongoClient = new MongoClient(
                        new ServerAddress(SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.HOST")), Collections.singletonList(credential)
                );
            } else {
                mongoClient = new MongoClient(
                        new ServerAddress(SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.HOST"))
                );
            }
            mongoDatabase = mongoClient.getDatabase(SkyWarsCommon.INSTANCE.getConfigFile().getString("MONGO.DATABASE"));
            statsCollection = mongoDatabase.getCollection("stats");
        } catch (Exception e) {
            Log.show(Log.LogLevel.EXTREME,"無法連接到 MongoDB");
        }*/

        return CompletableFuture.supplyAsync(() -> {
            try {
                mongoClient = new MongoClient(Streamer.INSTANCE.getConfig().getMongoHost());
                mongoDatabase = mongoClient.getDatabase("streamer");
                mongoCollection = mongoDatabase.getCollection("data");
                isEnabled = true;
            } catch (Exception e) {
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(CC.RED + "無法連接到 MongoDB"));
                isEnabled = false;
            }
            return isEnabled;
        });
    }

    public <T> T getData(String username, Class<T> clazz) {
        return clazz.cast(mongoCollection.find(Filters.eq("username", username)).first());
    }

    public <T> T getDataByDiscordID(String ID, Class<T> clazz) {
        return clazz.cast(mongoCollection.find(Filters.eq("discord_id", ID)).first());
    }

    public CompletableFuture<List<RetrievedDocument>> retrievePlayers() {
        List<RetrievedDocument> users = new ArrayList<>();
        return CompletableFuture.supplyAsync(() -> {
            if (Streamer.INSTANCE.getMongoDB().getMongoCollection().countDocuments() <= 0) {
                return users;
            }
            for (Document doc : getMongoCollection().find()) {
                users.add(new RetrievedDocument(doc.getString("username"), doc.getString("discord_id"), doc.getLong("save_time")));
            }
            return users;
        });
    }
}
