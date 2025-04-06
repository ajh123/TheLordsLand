package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import me.ajh123.the_lords_land.content.voting.system.Poll;
import net.minecraft.network.FriendlyByteBuf;

public class VoteScreenData implements ByteBufConvertable {
    private Poll poll;

    public VoteScreenData() {
    }

    public VoteScreenData(Poll poll) {
        this.poll = poll;
    }

    @Override
    public void decode(FriendlyByteBuf buf) {

    }

    @Override
    public void encode(FriendlyByteBuf buf) {

    }

    public Poll getPoll() {
        return poll;
    }
}
