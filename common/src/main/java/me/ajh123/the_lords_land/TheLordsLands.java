package me.ajh123.the_lords_land;

import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import me.ajh123.the_lords_land.foundation.ModRegistries;


public final class TheLordsLands {
    public static final String MOD_ID = "the_lords_land";
    public static LoaderCreativeTabs loaderCreativeTabs;

    public static void init() {
        ModRegistries.init();
        if (loaderCreativeTabs != null) {
            loaderCreativeTabs.init();
        }
    }
}
