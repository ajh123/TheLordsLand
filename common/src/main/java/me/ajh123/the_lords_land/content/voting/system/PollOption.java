package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.api.voting.IVoteResult;
import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PollOption implements ByteBufConvertable, IPollOption {
    private String description;
    private String title;
    private final List<IVoteResult> results;
    private int votes;

    public PollOption(String description, String title, List<IVoteResult> results) {
        this.description = description;
        this.title = title;
        // Create a defensive copy of the actions list.
        this.results = new ArrayList<>(results);
        this.votes = 0;
    }

    @Override
    public void addVote() {
        votes++;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<IVoteResult> getResults() {
        return Collections.unmodifiableList(results);
    }

    @Override
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
