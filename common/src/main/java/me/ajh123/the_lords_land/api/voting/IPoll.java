package me.ajh123.the_lords_land.api.voting;

import java.util.List;
import java.util.Optional;

/**
 * Represents a generic poll containing a title and a list of poll options.
 */
public interface IPoll {
    /**
     * Adds a poll option.
     *
     * @param option the poll option to add
     */
    void addOption(IPollOption option);

    /**
     * Records a vote for the specified poll option instance.
     *
     * @param option the poll option instance for which to cast the vote.
     * @throws IllegalArgumentException if the option is not part of this poll.
     */
    void vote(IPollOption option);

    /**
     * Determines the winning poll option based on the voting condition.
     *
     * @return an Optional containing the winning poll option, or empty if none qualifies.
     */
    Optional<IPollOption> determineWinner();

    /**
     * Executes the results associated with the winning poll option.
     */
    void executeWinningResults();

    /**
     * Returns the title of the poll.
     */
    String getTitle();

    /**
     * Returns an unmodifiable list of poll options.
     */
    List<IPollOption> getOptions();

    /**
     * Checks if the poll is closed.
     *
     * @return true if the poll is closed, false otherwise
     */
    boolean isClosed();
}
