package com.conkcreets.reelresources.registers;

import com.conkcreets.reelresources.ReelResources;
import com.conkcreets.reelresources.loottables.FishingTable;
import com.mojang.serialization.MapCodec;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModLoot {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = 
        DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, ReelResources.MODID);
    
    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<FishingTable>> FISHING_TABLE = LOOT_MODIFIERS.register("fishing_table", () -> FishingTable.CODEC);

    public static void register(IEventBus modEventBus) {
        LOOT_MODIFIERS.register(modEventBus);
    }
}
