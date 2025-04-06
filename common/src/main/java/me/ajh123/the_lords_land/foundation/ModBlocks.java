package me.ajh123.the_lords_land.foundation;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static me.ajh123.the_lords_land.foundation.ModRegistries.BLOCKS;

public class ModBlocks {
    public static final RegistrySupplier<Block> BALLOT_BOX = BLOCKS.register("ballot_box", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F, 3.0F)));

    public static void init() {
        // This method is intentionally left empty. The blocks are registered statically.
    }
}
