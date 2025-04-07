package me.ajh123.the_lords_land.api.internal;

import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;

import java.util.UUID;

public interface PlayerMixinWrapper {
    void the_lords_land$openVoteScreen(VoteScreenData data);
    UUID the_lords_land$getPlayerId();
}
