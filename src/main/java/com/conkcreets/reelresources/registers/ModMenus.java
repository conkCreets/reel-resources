package com.conkcreets.reelresources.registers;

import java.util.function.Supplier;

import com.conkcreets.reelresources.ReelResources;
import com.conkcreets.reelresources.menus.AquariumMenu;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, ReelResources.MODID);

    public static final Supplier<MenuType<AquariumMenu>> AQUARIUM_MENU = MENUS.register("aquarium_menu", () -> IMenuTypeExtension.create(AquariumMenu::new));

    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }
}
