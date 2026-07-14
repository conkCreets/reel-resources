package com.conkcreets.reelresources.blockentities;

import java.util.Optional;

import com.conkcreets.reelresources.customrecipes.AquariumRecipe;
import com.conkcreets.reelresources.menus.AquariumMenu;
import com.conkcreets.reelresources.registers.ModBlockEntities;
import com.conkcreets.reelresources.registers.ModRecipes;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.SingleRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public class AquariumBlockEntity extends BaseContainerBlockEntity {

    public static final int SIZE = 2;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    private int tickCounter = 0;

    public AquariumBlockEntity(BlockPos pos, BlockState blockState) 
    {
        super(ModBlockEntities.AQUARIUM_BLOCK_ENTITY.get(), pos, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, AquariumBlockEntity entity) 
    {
        ItemStack stack = entity.getItem(0);
        if (level.isClientSide()) 
        {
            return;
        }
        ServerLevel serverLevel = (ServerLevel) level;

        if (stack.isEmpty()) 
        {
            entity.tickCounter = 0;
            return;
        }
        SingleRecipeInput recipeInput = new SingleRecipeInput(stack);
        
        Optional<RecipeHolder<AquariumRecipe>> recipeHolder = serverLevel.recipeAccess().getRecipeFor(ModRecipes.AQUARIUM_RECIPE_TYPE.get(), recipeInput, serverLevel);
        if (recipeHolder.isEmpty()) 
        {
            entity.tickCounter = 0;
            return;
        }
        AquariumRecipe recipe = recipeHolder.get().value();

        entity.tickCounter++;
        if (entity.tickCounter % 20 == 0) 
        {
            entity.setChanged();
        }
        if (entity.tickCounter >= recipe.getTicksToProcess()) 
        {
            ItemStack outputStack = entity.getItem(1);
            if (outputStack.isEmpty()) 
            {
                entity.setItem(1, recipe.assemble(recipeInput, serverLevel.registryAccess()));
                entity.tickCounter = 0;
            }
            else if (outputStack.getCount() == outputStack.getMaxStackSize())
            {
                return;
            }
            else if (outputStack.getItem() == recipe.getOutput().getItem())
            {
                outputStack.grow(1);
                entity.setChanged();
                entity.tickCounter = 0;
            }
            else
            {
                return;
            }
        }

        
    }
    
    // Container getter
    @Override
    public int getContainerSize() 
    {
        return SIZE;
    }

    // ItemStack getter
    @Override
    protected NonNullList<ItemStack> getItems() 
    {
        return items;
    }

    // ItemStack setter
    @Override
    protected void setItems(NonNullList<ItemStack> items) 
    {
        this.items = items;
    }

    // Default name for the container
    @Override
    protected Component getDefaultName() 
    {
        return Component.translatable("block.reelresources.aquarium_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) 
    {
        return new AquariumMenu(containerId, playerInventory, this, ContainerLevelAccess.create(level, worldPosition));
    }

    @Override
    protected void saveAdditional(ValueOutput output)
    {
        super.saveAdditional(output);
        output.putInt("tickCounter", this.tickCounter);
        var itemsList = output.list("items", ItemStack.CODEC);
        for (ItemStack stack : this.items) 
        {
            itemsList.add(stack);
        }
    }

    @Override
    protected void loadAdditional(ValueInput input)
    {
        super.loadAdditional(input);
        this.tickCounter = input.getIntOr("tickCounter", 0);
        this.items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
        int i = 0;
        for (ItemStack stack : input.listOrEmpty("items", ItemStack.CODEC)) 
        {
            if (i < SIZE) {
                this.items.set(i++, stack);
            }
        }
    }
}
