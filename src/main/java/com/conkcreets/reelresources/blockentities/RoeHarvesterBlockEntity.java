package com.conkcreets.reelresources.blockentities;

import com.conkcreets.reelresources.menus.RoeHarvesterMenu;
import com.conkcreets.reelresources.registers.ModBlockEntities;

import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Items;

public class RoeHarvesterBlockEntity extends BaseContainerBlockEntity {

    public static final int SIZE = 2;
    private NonNullList<ItemStack> items = NonNullList.withSize(SIZE, ItemStack.EMPTY);
    public RoeHarvesterBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntities.ROE_HARVESTER.get(), pos, blockState);
    }

    private int processTime = 0;
    
public static void tick(Level level, BlockPos pos, BlockState state, RoeHarvesterBlockEntity blockEntity) {
        ItemStack roeSlot = blockEntity.getItem(0);
        ItemStack outputSlot = blockEntity.getItem(1);

        if (!roeSlot.isEmpty() && outputSlot.isEmpty()) {
            blockEntity.processTime++;

            if (blockEntity.processTime >= 200) {
                roeSlot.shrink(1);
                blockEntity.setItem(1, new ItemStack(Items.IRON_INGOT));
                blockEntity.processTime = 0;
                blockEntity.setChanged();
            }
        } else {
            blockEntity.processTime = 0;
        }

    }

    @Override
    public int getContainerSize() {
        return SIZE;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> items) {
        this.items = items;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.reelresources.roe_harvester");
    }

    @Override
    protected AbstractContainerMenu createMenu(int containerId, Inventory playerInventory) {
        return new RoeHarvesterMenu(containerId, playerInventory, this, ContainerLevelAccess.create(level, worldPosition));
    }
}

