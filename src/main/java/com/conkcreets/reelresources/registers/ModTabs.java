package com.conkcreets.reelresources.registers;

import com.conkcreets.reelresources.ReelResources;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTabs {
        // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "reelresources" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ReelResources.MODID);


    // Creates a creative tab with the id "reelresources:reel_fish_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REEL_FISH_TAB = CREATIVE_MODE_TABS.register("reel_fish_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.reelresources.reel_fish_tab")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
            .icon(() -> ModItems.IRON_FISH.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.IRON_FISH.get());
                output.accept(ModItems.GOLD_FISH.get());
                output.accept(ModItems.COPPER_FISH.get());
            }).build());
    
    // Creates a creative tab with the id "reelresources:reel_equipment_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> REEL_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("reel_equipment_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.reelresources.reel_equipment_tab")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(REEL_FISH_TAB.getKey())
            .icon(() -> ModBlocks.AQUARIUM_BLOCK_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.AQUARIUM_BLOCK_ITEM.get());
            }).build());

    public static void register(IEventBus modEventBus) {
        CREATIVE_MODE_TABS.register(modEventBus);
    }
}
