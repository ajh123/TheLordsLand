package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.api.voting.IPoll;
import me.ajh123.the_lords_land.api.voting.IPollManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PollManager implements IPollManager {
    private final Map<String, IPoll> polls = new HashMap<>();

    @Override
    public void addPoll(IPoll poll) {
        if (polls.containsKey(poll.getTitle())) {
            throw new IllegalArgumentException("A poll with the title '" + poll.getTitle() + "' already exists.");
        }
        polls.put(poll.getTitle(), poll);
    }

    @Override
    public Optional<IPoll> getPoll(String title) {
        return Optional.ofNullable(polls.get(title));
    }

    @Override
    public boolean removePoll(String title) {
        return polls.remove(title) != null;
    }

    @Override
    public boolean hasPoll(String title) {
        return polls.containsKey(title);
    }

    @Override
    public Map<String, IPoll> getAllPolls() {
        return Map.copyOf(polls);
    }
}