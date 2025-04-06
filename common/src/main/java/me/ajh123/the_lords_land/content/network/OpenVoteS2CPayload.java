package me.ajh123.the_lords_land.content.network;

import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.api.LordsLandPlayer;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import me.ajh123.the_lords_land.content.voting.system.Poll;
import net.fabricmc.api.EnvType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record OpenVoteS2CPayload(VoteScreenData data) implements CustomPacketPayload {
    public static final String OPEN_VOTE_S2C = TheLordsLands.MOD_ID+"/open_vote";
    public static final StreamCodec<FriendlyByteBuf, OpenVoteS2CPayload> CODEC = CustomPacketPayload.codec(OpenVoteS2CPayload::write, OpenVoteS2CPayload::new);
    public static final CustomPacketPayload.Type<OpenVoteS2CPayload> TYPE = CustomPacketPayload.createType(OPEN_VOTE_S2C);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private OpenVoteS2CPayload(FriendlyByteBuf friendlyByteBuf) {
        this(new VoteScreenData(new Poll(null, null)));
        this.data.decode(friendlyByteBuf);
    }

    private void write(FriendlyByteBuf friendlyByteBuf) {
        this.data.encode(friendlyByteBuf);
    }

    public static void receive(OpenVoteS2CPayload value, NetworkManager.PacketContext context) {
        if (context.getEnv() == EnvType.CLIENT) {
            VoteScreenData data = value.data;
            if (context.getPlayer() instanceof LordsLandPlayer player) {
                player.the_lords_land$openVoteScreen(data);
            }
        }
    }
}
