package me.ajh123.the_lords_land.content.voting.interactions;

import me.ajh123.the_lords_land.api.voting.IPoll;
import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;

public record VoteScreenData(IPoll poll) implements ByteBufConvertable {

    @Override
    public void decode(FriendlyByteBuf buf) {
        if (poll instanceof ByteBufConvertable byteBufConvertable)
            byteBufConvertable.decode(buf);
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        if (poll instanceof ByteBufConvertable byteBufConvertable)
            byteBufConvertable.encode(buf);
    }
}
