package me.ajh123.the_lords_land.api.contracts;


import me.ajh123.the_lords_land.api.IPlayer;
import net.minecraft.network.chat.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Represents an in-game legal contract that can be signed by multiple players.
 * <p>
 * A contract contains multiple pages of content (as Components) and stores signatures,
 * including the signing date for each player.
 */
public interface IContract {
    /**
     * Signs the contract with the given player.
     *
     * @param player the player signing the contract
     * @return true if the contract was successfully signed, false if the player has already signed or signing failed
     */
    boolean sign(IPlayer player);

    /**
     * Returns an unmodifiable map of players who have signed the contract along with their signing dates.
     *
     * @return am unmodifiable map where the keys are players who signed the contract and the values are the corresponding signing dates.
     */
    Map<IPlayer, LocalDateTime> getSignatures();

    /**
     * Returns the pages of content contained in the contract.
     *
     * @return a list of Components where each Component representing a contract's pages
     */
    List<Component> getPages();
}
