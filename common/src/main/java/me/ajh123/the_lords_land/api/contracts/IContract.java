package me.ajh123.the_lords_land.api.contracts;


import me.ajh123.the_lords_land.api.IPlayer;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents an in-game legal contract that can be signed by a single player.
 * <p>
 * A contract contains multiple pages of content (as Components) and stores signatures,
 * including the signing date for the player.
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
     * Checks if the contract has been signed by the given player.
     *
     * @param player the player to check
     * @return true if the player has signed the contract, false otherwise
     */
    boolean isSignedBy(IPlayer player);

    /**
     * Returns the date when the contract was signed by the given player.
     *
     * @param player the player whose signing date is requested
     * @return the date when the contract was signed, or null if not signed by the player
     */
    @Nullable Instant getSigningDate(IPlayer player);

    /**
     * Returns the pages of content contained in the contract.
     *
     * @return a list of Strings where each String representing a contract's pages
     */
    List<String> getPages();
}
