package me.ajh123.the_lords_land.content.voting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a poll option that contains a description, a list of actions to execute,
 * and a vote count.
 */
public class PollOption {
    private final String description;
    private final List<VoteResult> results;
    private int votes;

    public PollOption(String description, List<VoteResult> results) {
        this.description = description;
        // Create a defensive copy of the actions list.
        this.results = new ArrayList<>(results);
        this.votes = 0;
    }

    /**
     * Increments the vote count for this option.
     */
    public void addVote() {
        votes++;
    }

    /**
     * Returns the description of this option.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns an unmodifiable view of the results.
     */
    public List<VoteResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    /**
     * Returns the current vote count for this option.
     */
    public int getVotes() {
        return votes;
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "description='" + description + '\'' +
                ", votes=" + votes +
                '}';
    }
}
