package com.conkcreets.reelresources.customrecipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class AquariumRecipeSerializer implements RecipeSerializer<AquariumRecipe> {

    public static final MapCodec<AquariumRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> 
        instance.group(
            Ingredient.CODEC.fieldOf("input").forGetter(AquariumRecipe::getInput),
            ItemStack.CODEC.fieldOf("output").forGetter(AquariumRecipe::getOutput),
            Codec.INT.fieldOf("ticks_to_process").forGetter(AquariumRecipe::getTicksToProcess)
        ).apply(instance, AquariumRecipe::new)
    );
    public AquariumRecipeSerializer() {
    }

    @Override
    public MapCodec<AquariumRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, AquariumRecipe> streamCodec() {
        return StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, AquariumRecipe::getInput,
            ItemStack.STREAM_CODEC, AquariumRecipe::getOutput,
            ByteBufCodecs.INT, AquariumRecipe::getTicksToProcess,
            AquariumRecipe::new
        );
    }
    
}
