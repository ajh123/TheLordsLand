package me.ajh123.the_lords_land.fabric;

import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.content.network.OpenVoteS2CPayload;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public final class TheLordsLandsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        TheLordsLands.loaderCreativeTabs = new LoaderCreativeTabsFabric();

        // Run our common setup.
        TheLordsLands.init();


        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("vote").executes(context -> {
                if (context.getSource().isPlayer()) {
                    ServerPlayer player = context.getSource().getPlayer();
                    if (player != null) {
                        NetworkManager.sendToPlayer(player, new OpenVoteS2CPayload(new VoteScreenData(TheLordsLands.getTestPoll())));
                    }
                }


                return 1;
            }));
        });
    }
}
