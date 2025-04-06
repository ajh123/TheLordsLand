package me.ajh123.the_lords_land.neoforge;

import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import me.ajh123.the_lords_land.foundation.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

public class LoaderCreativeTabsNeoForge extends LoaderCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            TheLordsLands.MOD_ID
    );

    @Override
    public void init() {
        CREATIVE_MODE_TABS.register("main", () -> CreativeModeTab.builder()
                .title(MAIN_TAB)
                .icon(() -> new ItemStack(ModItems.BALLOT))
                .displayItems((params, output) -> {
                    for (ResourceKey<Item> itemKey : BuiltInRegistries.ITEM.registryKeySet()) {
                        Item item = BuiltInRegistries.ITEM.get(itemKey);
                        if (itemKey.location().getNamespace().equals(TheLordsLands.MOD_ID)) {
                            if (item != null) {
                                output.accept(item);
                            }
                        }
                    }
                })
                .build()
        );
    }
}
