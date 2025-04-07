package me.ajh123.the_lords_land.mixin;

import com.mojang.authlib.GameProfile;
import me.ajh123.the_lords_land.api.internal.PlayerMixinWrapper;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreen;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin extends AbstractClientPlayer implements PlayerMixinWrapper {
    @Shadow
    @Final
    protected Minecraft minecraft;

    public LocalPlayerMixin(ClientLevel clientLevel, GameProfile gameProfile) {
        super(clientLevel, gameProfile);
    }

    @Unique
    @Override
    public void the_lords_land$openVoteScreen(VoteScreenData data) {
        minecraft.setScreen(new VoteScreen(data));
    }
}