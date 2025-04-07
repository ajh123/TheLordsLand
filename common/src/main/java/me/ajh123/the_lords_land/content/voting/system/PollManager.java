package me.ajh123.the_lords_land.content.voting.system;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Manages a collection of polls, mapping poll titles to their respective Poll objects.
 */
public class PollManager {
    private final Map<String, Poll> polls = new HashMap<>();

    /**
     * Adds a poll to the manager.
     *
     * @param poll the poll to add
     * @throws IllegalArgumentException if a poll with the same title already exists
     */
    public void addPoll(Poll poll) {
        if (polls.containsKey(poll.getTitle())) {
            throw new IllegalArgumentException("A poll with the title '" + poll.getTitle() + "' already exists.");
        }
        polls.put(poll.getTitle(), poll);
    }

    /**
     * Retrieves a poll by its title.
     *
     * @param title the title of the poll
     * @return an Optional containing the poll if found, or empty if not found
     */
    public Optional<Poll> getPoll(String title) {
        return Optional.ofNullable(polls.get(title));
    }

    /**
     * Removes a poll by its title.
     *
     * @param title the title of the poll to remove
     * @return true if the poll was removed, false if no poll with the given title exists
     */
    public boolean removePoll(String title) {
        return polls.remove(title) != null;
    }

    /**
     * Checks if a poll with the given title exists.
     *
     * @param title the title of the poll
     * @return true if the poll exists, false otherwise
     */
    public boolean hasPoll(String title) {
        return polls.containsKey(title);
    }

    /**
     * Returns all polls managed by this PollManager.
     *
     * @return a map of poll titles to Poll objects
     */
    public Map<String, Poll> getAllPolls() {
        return Map.copyOf(polls);
    }
}