package me.ajh123.the_lords_land.compat.loader;

import me.ajh123.the_lords_land.TheLordsLands;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public abstract class LoaderCreativeTabs {
    public static Component MAIN_TAB = Component.translatable("itemGroup." + TheLordsLands.MOD_ID + ".main");

    protected static ResourceKey<CreativeModeTab> createKey(String string) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(TheLordsLands.MOD_ID, string));
    }

    public abstract void init();
}
