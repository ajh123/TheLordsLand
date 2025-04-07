package me.ajh123.the_lords_land.api.voting;

import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.api.contracts.IContract;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Represents a poll option that contains a description, a list of actions to execute,
 * and a vote count.
 */
public interface IPollOption {
    /**
     * Returns the title of this option.
     */
    String getTitle();

    /**
     * Returns the description of this option.
     */
    String getDescription();

    /**
     * Returns an unmodifiable view of the results.
     */
    List<IVoteResult> getResults();

    /**
     * Returns the current vote count for this option.
     */
    int getVotes();

    /**
     * Returns the contract associated with this option.
     *
     * @param player The player requesting the contract.
     */
    IContract getContract(IPlayer player);

    /**
     * Signs this option for the given player.
     *
     * @param player The player signing the option.
     * @return true if the signature was successful, false otherwise.
     */
    boolean sign(IPlayer player);

    /**
     * Returns the signatures of this option.
     *
     * @return A map of players who signed this option and the time they signed it.
     */
    Map<IPlayer, LocalDateTime> getSignatures();
}
