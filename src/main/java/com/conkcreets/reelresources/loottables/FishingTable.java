package com.conkcreets.reelresources.loottables;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class FishingTable extends LootModifier{

    private final Item item;
    public static final MapCodec<FishingTable> CODEC = RecordCodecBuilder.mapCodec(instance ->
        codecStart(instance).and(
            BuiltInRegistries.ITEM.byNameCodec().fieldOf("item").forGetter(FishingTable::getItem)
        ).apply(instance, FishingTable::new)
    );

    private Item getItem() {
        return item;
    }

    public FishingTable(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> lootTable, LootContext context) {
        lootTable.add(new ItemStack(item));
        return lootTable;
    }
    
}
