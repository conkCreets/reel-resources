package com.conkcreets.reelresources.loottables;

import com.conkcreets.reelresources.registers.ModItems;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class FishingTable extends LootModifier{

    public static final MapCodec<FishingTable> CODEC = RecordCodecBuilder.mapCodec(instance -> 
        codecStart(instance).apply(instance, FishingTable::new)
    );

    public FishingTable(LootItemCondition[] conditionsIn) {
        super(conditionsIn);
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> lootTable, LootContext context) {
        lootTable.add(new ItemStack(ModItems.IRON_FISH.get()));
        return lootTable;
    }
    
}
