package me.ajh123.the_lords_land.fabric;

import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.foundation.ModItems;
import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LoaderCreativeTabsFabric extends LoaderCreativeTabs {
    private static final ResourceKey<CreativeModeTab> MAIN = createKey("main");

    @Override
    public void init() {
        CreativeModeTab mainTab = FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.BALLOT))
                .title(MAIN_TAB)
                .build();

        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, MAIN, mainTab);

        ItemGroupEvents.modifyEntriesEvent(MAIN).register(entries -> {
            for (ResourceKey<Item> itemKey : BuiltInRegistries.ITEM.registryKeySet()) {
                Item item = BuiltInRegistries.ITEM.get(itemKey);
                if (itemKey.location().getNamespace().equals(TheLordsLands.MOD_ID)) {
                    entries.accept(item);
                }
            }
        });
    }
}
