package me.ajh123.the_lords_land.content.voting.conditions;

import me.ajh123.the_lords_land.content.voting.VotingCondition;

// A concrete implementation of VotingCondition for a simple majority requirement.
// For example, for a 2/3 majority, majorityFraction would be 2.0 / 3.
public class MajorityCondition implements VotingCondition {
    private final double majorityFraction;

    public MajorityCondition(double majorityFraction) {
        this.majorityFraction = majorityFraction;
    }

    @Override
    public boolean isWinner(int votesForOption, int totalVotes) {
        return totalVotes > 0 && votesForOption >= totalVotes * majorityFraction;
    }
}