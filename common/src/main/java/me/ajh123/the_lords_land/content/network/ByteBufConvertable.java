package me.ajh123.the_lords_land.content.network;

import net.minecraft.network.FriendlyByteBuf;

public interface ByteBufConvertable {
    void decode(FriendlyByteBuf buf);

    void encode(FriendlyByteBuf buf);
}
