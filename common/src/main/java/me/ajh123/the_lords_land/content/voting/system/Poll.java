package me.ajh123.the_lords_land.content.voting.system;

import me.ajh123.the_lords_land.api.voting.IPoll;
import me.ajh123.the_lords_land.api.voting.IPollOption;
import me.ajh123.the_lords_land.api.voting.IVoteResult;
import me.ajh123.the_lords_land.api.voting.IVotingCondition;
import me.ajh123.the_lords_land.content.network.ByteBufConvertable;
import net.minecraft.network.FriendlyByteBuf;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Poll implements ByteBufConvertable, IPoll {
    private String title;
    private final List<IPollOption> options = new ArrayList<>();
    private final IVotingCondition votingCondition;
    private boolean isClosed = false;

    public Poll(String title, IVotingCondition votingCondition) {
        this.title = title;
        this.votingCondition = votingCondition;
    }

    @Override
    public void addOption(IPollOption option) {
        if (isClosed) {
            throw new IllegalStateException("Poll is closed. Cannot add an option.");
        }
        options.add(option);
    }

    @Override
    public void vote(IPollOption option) {
        if (isClosed) {
            throw new IllegalStateException("Poll is closed. Cannot vote.");
        }
        if (!options.contains(option)) {
            throw new IllegalArgumentException("The provided option is not part of this poll.");
        }
        option.addVote();
    }

    @Override
    public Optional<IPollOption> determineWinner() {
        if (isClosed) {
            throw new IllegalStateException("Poll is already closed.");
        }
        isClosed = true;
        int totalVotes = options.stream().mapToInt(IPollOption::getVotes).sum();
        for (IPollOption option : options) {
            if (votingCondition.isWinner(option.getVotes(), totalVotes)) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }

    @Override
    public void executeWinningResults() {
        Optional<IPollOption> winningOption = determineWinner();
        if (winningOption.isPresent()) {
            IPollOption option = winningOption.get();
            System.out.println("Winning option: " + option.getDescription());
            option.getResults().forEach(IVoteResult::execute);
        } else {
            System.out.println("No option met the winning criteria.");
        }
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<IPollOption> getOptions() {
        return List.copyOf(options);
    }

    @Override
    public void decode(FriendlyByteBuf buf) {
        this.title = buf.readUtf();
        int size = buf.readInt();
        for (int i = 0; i < size; i++) {
            PollOption option = new PollOption("", "", new ArrayList<>());
            option.decode(buf);
            options.add(option);
        }
    }

    @Override
    public void encode(FriendlyByteBuf buf) {
        buf.writeUtf(title);
        buf.writeInt(options.size());
        for (IPollOption option : options) {
            if (option instanceof ByteBufConvertable byteBufConvertable)
                byteBufConvertable.encode(buf);
        }
    }


    @Override
    public boolean isClosed() {
        return isClosed;
    }
}
