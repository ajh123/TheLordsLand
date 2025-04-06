package me.ajh123.the_lords_land.neoforge;

import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.compat.loader.CreativeTabMeta;
import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import me.ajh123.the_lords_land.foundation.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class LoaderCreativeTabsNeoForge extends LoaderCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            TheLordsLands.MOD_ID
    );

    @Override
    public void init() {
        List<CreativeTabMeta> creativeTabMetaList = getCreativeTabMetaList();

        for (CreativeTabMeta meta : creativeTabMetaList) {
            CREATIVE_MODE_TABS.register(meta.getName(), () -> CreativeModeTab.builder()
                    .title(meta.getDisplayName())
                    .icon(() -> new ItemStack(meta.getIcon()))
                    .displayItems((params, output) -> {
                        CreativeTabMeta.Output output2 = new CreativeTabMeta.Output();
                        meta.getTabItemsGenerator().accept(output2);

                        for (Item item : output2.getItems()) {
                            output.accept(item);
                        }
                    })
                    .build()
            );
        }
    }
}
