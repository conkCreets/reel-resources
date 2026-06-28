package com.conkcreets.reelresources.blockentities;

import com.conkcreets.reelresources.menus.AquariumMenu;
import com.conkcreets.reelresources.registers.ModBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class AquariumBlockEntity extends BaseContainerBlockEntity {

    public static final int SIZE = 2;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    public AquariumBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.AQUARIUM_BLOCK_ENTITY.get(), pos, blockState);
    }
    
    // Container getter
    @Override
    public int getContainerSize() {
        return SIZE;
    }

    // ItemStack getter
    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    // ItemStack setter
    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    // Default name for the container
    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.reelresources.aquarium_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new AquariumMenu(containerId, playerInventory, this, ContainerLevelAccess.create(level, worldPosition));
    }
}
