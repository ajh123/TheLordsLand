package me.ajh123.the_lords_land.api.voting;

public interface IVotingCondition {
    /**
     * Determines whether an option wins based on its vote count and the total votes.
     *
     * @param votesForOption the number of votes the option received
     * @param totalVotes the total number of votes cast in the poll
     * @return true if the option meets the winning criteria, false otherwise
     */
    boolean isWinner(int votesForOption, int totalVotes);
}
