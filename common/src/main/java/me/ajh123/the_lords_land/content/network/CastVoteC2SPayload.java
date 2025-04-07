package me.ajh123.the_lords_land.content.network;

import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.content.voting.interactions.CastVoteData;
import net.fabricmc.api.EnvType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import org.jetbrains.annotations.NotNull;

public record CastVoteC2SPayload(CastVoteData data) implements CustomPacketPayload {
    public static final String CAST_VOTE_C2S = TheLordsLands.MOD_ID+"/cast_vote";
    public static final StreamCodec<FriendlyByteBuf, CastVoteC2SPayload> CODEC = CustomPacketPayload.codec(CastVoteC2SPayload::write, CastVoteC2SPayload::new);
    public static final CustomPacketPayload.Type<CastVoteC2SPayload> TYPE = CustomPacketPayload.createType(CAST_VOTE_C2S);

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    private CastVoteC2SPayload(FriendlyByteBuf friendlyByteBuf) {
        this(new CastVoteData());
        this.data.decode(friendlyByteBuf);
    }

    private void write(FriendlyByteBuf friendlyByteBuf) {
        this.data.encode(friendlyByteBuf);
    }

    public static void receive(CastVoteC2SPayload value, NetworkManager.PacketContext context) {
        if (context.getEnv() == EnvType.SERVER) {
            CastVoteData data = value.data;

            TheLordsLands.pollManager.getPoll(data.getVoteName()).ifPresent(poll -> {
                if (poll.isClosed()) {
                    System.out.println("Poll is closed. Cannot vote.");
                    return;
                }
                if (data.getVoteOption() < 0 || data.getVoteOption() >= poll.getOptions().size()) {
                    System.out.println("Invalid option index.");
                    return;
                }
                IPlayer player = IPlayer.getPlayer(context.getPlayer());
                poll.vote(player, poll.getOptions().get(data.getVoteOption()));
            });
        }
    }
}
