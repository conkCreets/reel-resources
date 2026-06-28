package com.conkcreets.reelresources.registers;

import java.util.function.Supplier;

import com.conkcreets.reelresources.ReelResources;
import com.conkcreets.reelresources.blockentities.AquariumBlockEntity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ReelResources.MODID);

    public static final Supplier<BlockEntityType<AquariumBlockEntity>> AQUARIUM_BLOCK_ENTITY = BLOCK_ENTITIES.register("aquarium_block_entity", () -> new BlockEntityType<AquariumBlockEntity>(AquariumBlockEntity::new, false, ModBlocks.AQUARIUM_BLOCK.get()));

    public static void register(IEventBus modEventBus) {
        BLOCK_ENTITIES.register(modEventBus);
    }
}
