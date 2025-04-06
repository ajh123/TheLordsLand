package me.ajh123.the_lords_land.fabric;

import me.ajh123.the_lords_land.compat.loader.CreativeTabMeta;
import me.ajh123.the_lords_land.compat.loader.LoaderCreativeTabs;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class LoaderCreativeTabsFabric extends LoaderCreativeTabs {
    @Override
    public void init() {
        List<CreativeTabMeta> creativeTabMetaList = getCreativeTabMetaList();

        for (CreativeTabMeta meta : creativeTabMetaList) {
            ResourceKey<CreativeModeTab> key = createKey(meta.getName());

            CreativeModeTab mainTab = FabricItemGroup.builder()
                    .icon(() -> new ItemStack(meta.getIcon()))
                    .title(meta.getDisplayName())
                    .build();

            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, mainTab);

            ItemGroupEvents.modifyEntriesEvent(key).register(entries -> {
                CreativeTabMeta.Output output = new CreativeTabMeta.Output();
                meta.getTabItemsGenerator().accept(output);

                for (Item item : output.getItems()) {
                    entries.accept(item);
                }
            });
        }
    }
}
