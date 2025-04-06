package me.ajh123.the_lords_land.content.voting;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents a generic poll containing a question and a list of poll options.
 */
public class Poll {
    private final String question;
    private final List<PollOption> options = new ArrayList<>();
    private final VotingCondition votingCondition;
    private boolean isClosed = false;

    public Poll(String question, VotingCondition votingCondition) {
        this.question = question;
        this.votingCondition = votingCondition;
    }

    /**
     * Adds a poll option.
     *
     * @param option the poll option to add
     */
    public void addOption(PollOption option) {
        if (isClosed) {
            throw new IllegalStateException("Poll is closed. Cannot add an option.");
        }
        options.add(option);
    }

    /**
     * Records a vote for the specified poll option instance.
     *
     * @param option the poll option instance for which to cast the vote.
     * @throws IllegalArgumentException if the option is not part of this poll.
     */
    public void vote(PollOption option) {
        if (isClosed) {
            throw new IllegalStateException("Poll is closed. Cannot vote.");
        }
        if (!options.contains(option)) {
            throw new IllegalArgumentException("The provided option is not part of this poll.");
        }
        option.addVote();
    }

    /**
     * Determines the winning poll option based on the voting condition.
     *
     * @return an Optional containing the winning poll option, or empty if none qualifies.
     */
    public Optional<PollOption> determineWinner() {
        if (isClosed) {
            throw new IllegalStateException("Poll is already closed.");
        }
        isClosed = true;
        int totalVotes = options.stream().mapToInt(PollOption::getVotes).sum();
        for (PollOption option : options) {
            if (votingCondition.isWinner(option.getVotes(), totalVotes)) {
                return Optional.of(option);
            }
        }
        return Optional.empty();
    }

    /**
     * Executes the results associated with the winning poll option.
     */
    public void executeWinningResults() {
        Optional<PollOption> winningOption = determineWinner();
        if (winningOption.isPresent()) {
            PollOption option = winningOption.get();
            System.out.println("Winning option: " + option.getDescription());
            option.getResults().forEach(VoteResult::execute);
        } else {
            System.out.println("No option met the winning criteria.");
        }
    }

    /**
     * Returns the question of the poll.
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Returns an unmodifiable list of poll options.
     */
    public List<PollOption> getOptions() {
        return List.copyOf(options);
    }
}
