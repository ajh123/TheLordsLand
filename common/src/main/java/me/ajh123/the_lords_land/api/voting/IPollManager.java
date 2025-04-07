package me.ajh123.the_lords_land.api.voting;

import java.util.Map;
import java.util.Optional;

/**
 * Manages a collection of polls, mapping poll titles to their respective Poll objects.
 */
public interface IPollManager {
    /**
     * Adds a poll to the manager.
     *
     * @param poll the poll to add
     * @throws IllegalArgumentException if a poll with the same title already exists
     */
    void addPoll(IPoll poll);

    /**
     * Retrieves a poll by its title.
     *
     * @param title the title of the poll
     * @return an Optional containing the poll if found, or empty if not found
     */
    Optional<IPoll> getPoll(String title);

    /**
     * Removes a poll by its title.
     *
     * @param title the title of the poll to remove
     * @return true if the poll was removed, false if no poll with the given title exists
     */
    boolean removePoll(String title);

    /**
     * Checks if a poll with the given title exists.
     *
     * @param title the title of the poll
     * @return true if the poll exists, false otherwise
     */
    boolean hasPoll(String title);

    /**
     * Returns all polls managed by this PollManager.
     *
     * @return a map of poll titles to Poll objects
     */
    Map<String, IPoll> getAllPolls();
}
