package me.ajh123.the_lords_land.fabric;

import me.ajh123.the_lords_land.TheLordsLands;
import net.fabricmc.api.ModInitializer;

public final class TheLordsLandsFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        TheLordsLands.loaderCreativeTabs = new LoaderCreativeTabsFabric();

        // Run our common setup.
        TheLordsLands.init();
    }
}
