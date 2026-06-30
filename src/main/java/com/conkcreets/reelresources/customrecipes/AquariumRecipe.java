package com.conkcreets.reelresources.customrecipes;

import com.conkcreets.reelresources.registers.ModRecipes;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.PlacementInfo;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;

public class AquariumRecipe implements Recipe<SingleRecipeInput> {

    private Ingredient input;
    private ItemStack output;
    private int ticksToProcess;

    public AquariumRecipe(Ingredient input, ItemStack output, int ticksToProcess) {
        this.input = input;
        this.output = output;
        this.ticksToProcess = ticksToProcess;
    }

    public Ingredient getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public int getTicksToProcess() {
        return ticksToProcess;
    }

    @Override
    public ItemStack assemble(SingleRecipeInput arg0, Provider arg1) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<SingleRecipeInput>> getSerializer() {
        return ModRecipes.AQUARIUM_RECIPE_SERIALIZER.get();
    }

    @Override
    public RecipeType<? extends Recipe<SingleRecipeInput>> getType() {
        return ModRecipes.AQUARIUM_RECIPE_TYPE.get();
    }

    @Override
    public boolean matches(SingleRecipeInput recipe, Level arg1) {
        return input.test(recipe.getItem(0));
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return ModRecipes.AQUARIUM_RECIPE_BOOK_CATEGORY.get();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
    
}
