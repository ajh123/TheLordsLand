package me.ajh123.the_lords_land.fabric;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import dev.architectury.networking.NetworkManager;
import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.api.voting.IPoll;
import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.content.network.OpenVoteS2CPayload;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

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
            dispatcher.register(Commands.literal("vote")
                .then(Commands.argument("pollTitle", StringArgumentType.string())
                    .executes(this::openVoteGUI)
                    .then(Commands.literal("stats")
                        .executes(this::viewPollStats))));
        });
    }

    private int openVoteGUI(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String pollTitle = StringArgumentType.getString(context, "pollTitle");
        IPoll poll = TheLordsLands.pollManager.getPoll(pollTitle).orElse(null);
    
        if (poll == null) {
            context.getSource().sendFailure(Component.literal("Poll not found: " + pollTitle));
            return 0;
        }
    
        ServerPlayer player = context.getSource().getPlayerOrException();
    
        // Send the OpenVoteS2CPayload packet with the poll's VoteScreenData
        NetworkManager.sendToPlayer(player, new OpenVoteS2CPayload(new VoteScreenData(poll)));
    
        context.getSource().sendSuccess(() -> Component.literal("Vote GUI opened for poll: " + pollTitle), false);
        return 1;
    }
    
    private int viewPollStats(CommandContext<CommandSourceStack> context) {
        String pollTitle = StringArgumentType.getString(context, "pollTitle");
        IPoll poll = TheLordsLands.pollManager.getPoll(pollTitle).orElse(null);
    
        if (poll == null) {
            context.getSource().sendFailure(Component.literal("Poll not found: " + pollTitle));
            return 0;
        }
    
        StringBuilder stats = new StringBuilder("Poll Statistics for: " + pollTitle + "\n");
        for (IPollOption option : poll.getOptions()) {
            stats.append(option.getTitle())
                .append(": ")
                .append(option.getVotes())
                .append(" votes\n");
        }
    
        context.getSource().sendSuccess(() -> Component.literal(stats.toString()), false);
        return 1;
    }
}
