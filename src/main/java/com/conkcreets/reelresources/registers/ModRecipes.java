package com.conkcreets.reelresources.registers;

import java.util.function.Supplier;

import com.conkcreets.reelresources.ReelResources;
import com.conkcreets.reelresources.customrecipes.AquariumRecipe;
import com.conkcreets.reelresources.customrecipes.AquariumRecipeSerializer;


import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ReelResources.MODID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ReelResources.MODID);
    public static final DeferredRegister<RecipeBookCategory> RECIPE_BOOK_CATEGORIES = DeferredRegister.create(Registries.RECIPE_BOOK_CATEGORY, ReelResources.MODID);

    public static final Supplier<RecipeSerializer<AquariumRecipe>> AQUARIUM_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("aquarium_recipe_serializer", AquariumRecipeSerializer::new);
    public static final Supplier<RecipeType<AquariumRecipe>> AQUARIUM_RECIPE_TYPE = RECIPE_TYPES.register("aquarium_recipe_type", RecipeType::simple);
    public static final Supplier<RecipeBookCategory> AQUARIUM_RECIPE_BOOK_CATEGORY = RECIPE_BOOK_CATEGORIES.register("aquarium_recipe_book_category", RecipeBookCategory::new);

    public static void register(IEventBus modEventBus) {
        RECIPE_SERIALIZERS.register(modEventBus);
        RECIPE_TYPES.register(modEventBus);
        RECIPE_BOOK_CATEGORIES.register(modEventBus);
    }
}
