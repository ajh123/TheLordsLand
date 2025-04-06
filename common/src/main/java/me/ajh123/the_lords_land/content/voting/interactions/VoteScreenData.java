package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import me.ajh123.the_lords_land.content.voting.system.Poll;
import net.minecraft.network.FriendlyByteBuf;

public record VoteScreenData(Poll poll) implements ByteBufConvertable {

    @Override
    public void decode(FriendlyByteBuf buf) {
        poll.decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        poll.encode(buf);
    }
}
