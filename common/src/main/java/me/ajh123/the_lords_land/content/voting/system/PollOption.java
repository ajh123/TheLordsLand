package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.api.IPlayer;
import me.ajh123.the_lords_land.api.contracts.IContract;
import me.ajh123.the_lords_land.api.internal.PlayerMixinWrapper;
import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.api.voting.IVoteResult;
import me.ajh123.the_lords_land.content.contract.BaseContract;
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
    private IContract contract;

    public PollOption(String description, String title, List<IVoteResult> results) {
        this.description = description;
        this.title = title;
        // Create a defensive copy of the actions list.
        this.results = new ArrayList<>(results);
        this.signatures = new HashMap<>();
        this.contract = new BaseContract(List.of(
            "This a contract between you and the issuer confirming you want to place your vote for "+title+".\n"
                    +"The conditions of which are as follows: \n\n" +description+"\n"+
                    "\n By signing this contract you acknowledge that you are willingly voting for this option and understand the consequences of your vote."
        ));
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
    public IContract getContract(IPlayer player) {
        return contract;
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
        this.contract = new BaseContract(List.of(
            "This a contract between you and the issuer confirming you want to place your vote for "+title+".\n"
                    +"The conditions of which are as follows: \n\n" +description+"\n"+
                    "\n By signing this contract you acknowledge that you are willingly voting for this option and understand the consequences of your vote."
        ));
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
}
