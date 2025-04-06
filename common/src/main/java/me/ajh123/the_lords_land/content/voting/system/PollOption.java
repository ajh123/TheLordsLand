package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a poll option that contains a description, a list of actions to execute,
 * and a vote count.
 */
public class PollOption implements ByteBufConvertable {
    private String description;
    private String title;
    private final List<VoteResult> results;
    private int votes;

    public PollOption(String description, String title, List<VoteResult> results) {
        this.description = description;
        this.title = title;
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
     * Returns the title of this option.
     */
    public String getTitle() {
        return title;
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
                ", title='" + title + '\'' +
                ", votes=" + votes +
                '}';
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.description = buf.readUtf();
        this.title = buf.readUtf();
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(this.description);
        buf.writeUtf(this.title);
    }
}
