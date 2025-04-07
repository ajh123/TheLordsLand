package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;

public class CastVoteData implements ByteBufConvertable {
    private String voteName;
    private int voteOption;

    public CastVoteData() {
    }

    public CastVoteData(String voteName, int voteOption) {
        this.voteName = voteName;
        this.voteOption = voteOption;
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.voteName = buf.readUtf();
        this.voteOption = buf.readInt();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(voteName);
        buf.writeInt(voteOption);
    }

    public String getVoteName() {
        return voteName;
    }

    public int getVoteOption() {
        return voteOption;
    }
}
