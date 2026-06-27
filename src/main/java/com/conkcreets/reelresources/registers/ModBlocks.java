package com.conkcreets.reelresources.registers;

import com.conkcreets.reelresources.ReelResources;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.item.BlockItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    // Create a Deferred Register to hold Blocks which will all be registered under the "reelresources" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ReelResources.MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "reelresources" namespace
    public static final DeferredRegister.Items BLOCK_ITEMS = DeferredRegister.createItems(ReelResources.MODID);


     // Creates a new Block with the id "reelresources:example_block", combining the namespace and path
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", p -> p.mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "reelresources:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = BLOCK_ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);
    // Creates a new Block with the id "reelresources:aquarium_block", combining the namespace and path
    public static final DeferredBlock<Block> AQUARIUM_BLOCK = BLOCKS.registerSimpleBlock("aquarium_block", p -> p.mapColor(MapColor.STONE));
    // Creates a new BlockItem with the id "reelresources:aquarium_block", combining the namespace and path
    public static final DeferredItem<BlockItem> AQUARIUM_BLOCK_ITEM = BLOCK_ITEMS.registerSimpleBlockItem("aquarium_block", AQUARIUM_BLOCK);

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        BLOCK_ITEMS.register(modEventBus);
    }
}
