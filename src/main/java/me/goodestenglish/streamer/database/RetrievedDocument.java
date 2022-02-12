package me.goodestenglish.streamer.database;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class RetrievedDocument {

    private final String username;
    private final String discordID;
    private final long saveTime;

}
