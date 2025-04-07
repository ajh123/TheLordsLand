package me.ajh123.the_lords_land.mixin;

import com.mojang.authlib.GameProfile;
import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.api.internal.PlayerMixinWrapper;
import me.ajh123.the_lords_land.content.network.OpenVoteS2CPayload;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ServerPlayer.class)
public abstract class ServerPlayerMixin extends Player implements PlayerMixinWrapper {
    public ServerPlayerMixin(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
        super(level, blockPos, f, gameProfile);
    }

    @Unique
    @Override
    public void the_lords_land$openVoteScreen(VoteScreenData data) {
        NetworkManager.sendToPlayer(((ServerPlayer)(Object)this), new OpenVoteS2CPayload(data));
    }
}