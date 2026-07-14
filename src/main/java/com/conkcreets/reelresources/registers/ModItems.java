package com.conkcreets.reelresources.registers;

import com.conkcreets.reelresources.ReelResources;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    // Create a Deferred Register to hold Items which will all be registered under the "reelresources" namespace
    public static final DeferredRegister.Items FISH_ITEMS = DeferredRegister.createItems(ReelResources.MODID);
    // Creates a new food item with the id "reelresources:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = FISH_ITEMS.registerSimpleItem("example_item", p -> p.food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));
    // New Iron Fish item as my initial custom item.
    public static final DeferredItem<Item> IRON_FISH = FISH_ITEMS.registerSimpleItem("iron_fish");
    public static final DeferredItem<Item> GOLD_FISH = FISH_ITEMS.registerSimpleItem("gold_fish");
    public static final DeferredItem<Item> COPPER_FISH = FISH_ITEMS.registerSimpleItem("copper_fish");
    // New Iron Roe item as my initial custom item. (CC)
    public static final DeferredItem<Item> IRON_ROE = FISH_ITEMS.registerSimpleItem("iron_roe");
    public static final DeferredItem<Item> GOLD_ROE = FISH_ITEMS.registerSimpleItem("gold_roe");
    public static final DeferredItem<Item> COPPER_ROE = FISH_ITEMS.registerSimpleItem("copper_roe");

    public static void register(IEventBus modEventBus) {
        FISH_ITEMS.register(modEventBus);
    }
}
