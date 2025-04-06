package me.ajh123.the_lords_land.compat.loader;

import me.ajh123.the_lords_land.TheLordsLands;
import me.ajh123.the_lords_land.foundation.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class LoaderCreativeTabs {
    public static CreativeTabMeta MAIN_TAB;

    private static final List<CreativeTabMeta> creativeTabMetaList = new ArrayList<>();

    public static void preInit() {
        MAIN_TAB = new CreativeTabMeta.Builder()
                .name("main")
                .icon(ModItems.BALLOT)
                .displayName(Component.translatable("itemGroup." + TheLordsLands.MOD_ID + ".main"))
                .tabItems((output) -> {
                    for (ResourceKey<Item> itemKey : BuiltInRegistries.ITEM.registryKeySet()) {
                        Item item = BuiltInRegistries.ITEM.get(itemKey);
                        if (itemKey.location().getNamespace().equals(TheLordsLands.MOD_ID)) {
                            if (item != null) {
                                output.accept(item);
                            }
                        }
                    }
                })
                .build();
        creativeTabMetaList.add(MAIN_TAB);
    }


    protected static ResourceKey<CreativeModeTab> createKey(String string) {
        return ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(TheLordsLands.MOD_ID, string));
    }

    public abstract void init();

    public List<CreativeTabMeta> getCreativeTabMetaList() {
        return creativeTabMetaList;
    }
}
