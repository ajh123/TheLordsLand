package me.ajh123.the_lords_land.neoforge;

import me.ajh123.the_lords_land.TheLordsLands;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TheLordsLands.MOD_ID)
public final class TheLordsLandsNeoForge {
    public TheLordsLandsNeoForge(IEventBus bus) {
        TheLordsLands.loaderCreativeTabs = new LoaderCreativeTabsNeoForge();

        // Run our common setup.
        TheLordsLands.init();
        LoaderCreativeTabsNeoForge.CREATIVE_MODE_TABS.register(bus);
    }
}
