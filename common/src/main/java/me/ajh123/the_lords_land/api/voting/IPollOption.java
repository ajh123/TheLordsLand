package me.ajh123.the_lords_land.api.voting;

import java.util.List;

/**
 * Represents a poll option that contains a description, a list of actions to execute,
 * and a vote count.
 */
public interface IPollOption {
    /**
     * Increments the vote count for this option.
     */
    void addVote();

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
}
