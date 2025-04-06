package me.ajh123.the_lords_land;

import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import me.ajh123.the_lords_land.content.voting.system.Poll;
import me.ajh123.the_lords_land.content.voting.system.PollOption;
import me.ajh123.the_lords_land.content.voting.system.conditions.MajorityCondition;
import me.ajh123.the_lords_land.foundation.ModRegistries;

import java.util.ArrayList;


public final class TheLordsLands {
    public static final String MOD_ID = "the_lords_land";
    public static LoaderCreativeTabs loaderCreativeTabs;

    public static void init() {
        ModRegistries.init();
        if (loaderCreativeTabs != null) {
            LoaderCreativeTabs.preInit();
            loaderCreativeTabs.init();
        }
    }


    public static Poll getTestPoll() {
        Poll poll = new Poll("test vote", new MajorityCondition(2.0 / 3));
        PollOption option1 = new PollOption("This may do something!" ,"Option 1", new ArrayList<>());
        PollOption option2 = new PollOption("Something else may happen from this.", "Option 2", new ArrayList<>());

        poll.addOption(option1);
        poll.addOption(option2);
        return poll;
    }
}
