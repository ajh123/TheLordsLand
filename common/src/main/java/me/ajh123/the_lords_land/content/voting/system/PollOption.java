package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.api.internal.PlayerMixinWrapper;
import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.api.voting.IVoteResult;
import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;

import java.time.LocalDateTime;
import java.util.*;

public class PollOption implements ByteBufConvertable, IPollOption {
    private String description;
    private String title;
    private final List<IVoteResult> results;
    private final Map<IPlayer, LocalDateTime> signatures;

    public PollOption(String description, String title, List<IVoteResult> results) {
        this.description = description;
        this.title = title;
        // Create a defensive copy of the actions list.
        this.results = new ArrayList<>(results);
        this.signatures = new HashMap<>();
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
        return signatures.size();
    }

    @Override
    public String toString() {
        return "PollOption{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", results=" + results +
                ", signatures=" + signatures +
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

    @Override
    public boolean sign(IPlayer player) {
        if (signatures.containsKey(player)) {
            return false; // Player has already signed
        }
        signatures.put(player, LocalDateTime.now());
        return true; // Successfully signed
    }

    @Override
    public Map<IPlayer, LocalDateTime> getSignatures() {
        return Collections.unmodifiableMap(signatures);
    }

    @Override
    public List<Component> getPages() {
        return List.of(
                Component.literal(description)
        );
    }
}
