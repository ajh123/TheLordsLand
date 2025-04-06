package me.ajh123.the_lords_land.foundation;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

import static me.ajh123.the_lords_land.foundation.ModRegistries.ITEMS;

public class ModItems {
    public static final RegistrySupplier<Item> BALLOT = ITEMS.register("ballot", () -> new Item(new Item.Properties()));
    public static final RegistrySupplier<Item> BALLOT_BOX = ITEMS.register("ballot_box", () -> new BlockItem(ModBlocks.BALLOT_BOX.get(), new Item.Properties()));

    public static void init() {
        // This method is intentionally left empty. The items are registered statically.
    }
}
