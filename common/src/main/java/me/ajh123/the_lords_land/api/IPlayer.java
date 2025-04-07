package me.ajh123.the_lords_land.api;

import me.ajh123.the_lords_land.api.internal.PlayerMixinWrapper;
import me.ajh123.the_lords_land.content.voting.interactions.VoteScreenData;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IPlayer {
    private static final Map<Player, IPlayer> playerMap = new HashMap<>();

    private final PlayerMixinWrapper playerMixin;

    public IPlayer(PlayerMixinWrapper playerMixin) {
        this.playerMixin = playerMixin;
    }

    public void openVoteScreen(VoteScreenData data) {
        playerMixin.the_lords_land$openVoteScreen(data);
    }

    public UUID getPlayerId() {
        return playerMixin.the_lords_land$getPlayerId();
    }

    public static IPlayer getPlayer(Player player) {
        if (player instanceof PlayerMixinWrapper) {
            if (playerMap.containsKey(player)) {
                return playerMap.get(player);
            }

            IPlayer iPlayer = new IPlayer((PlayerMixinWrapper) player);
            playerMap.put(player, iPlayer);
            return iPlayer;
        } else {
            throw new IllegalArgumentException("Player is not a LordsLandPlayer");
        }
    }

    @Nullable
    public static IPlayer getPlayer(UUID uuid) {
        for (Map.Entry<Player, IPlayer> entry : playerMap.entrySet()) {
            if (entry.getKey().getUUID().equals(uuid)) {
                return entry.getValue();
            }
        }
        return null;
    }
}
