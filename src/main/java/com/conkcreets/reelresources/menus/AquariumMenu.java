package com.conkcreets.reelresources.menus;

import com.conkcreets.reelresources.blockentities.AquariumBlockEntity;
import com.conkcreets.reelresources.registers.ModMenus;
import com.conkcreets.reelresources.registers.ModTags;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class AquariumMenu extends AbstractContainerMenu {

    private final ContainerLevelAccess access;
    
    public AquariumMenu(int containerId, Inventory playerInventory, Container blockInventory, ContainerLevelAccess access) {
        super(ModMenus.AQUARIUM_MENU.get(), containerId);
        this.access = access;
        addSlot(new FishInnerSlot(blockInventory, 0, 80, 35)); // fish input slot
        addSlot(new RoeInnerSlot(blockInventory, 1, 80, 55)); // roe output slot

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(playerInventory, col, 8 + col * 18, 142));
        }
    }

    public AquariumMenu(int containerId, Inventory playerInventory, FriendlyByteBuf extraData) {
        this(containerId, playerInventory, new SimpleContainer(AquariumBlockEntity.SIZE), ContainerLevelAccess.NULL);
        // Handle extra data if needed
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (!slot.hasItem()) 
        {
            return ItemStack.EMPTY;
        }
        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        if (index < AquariumBlockEntity.SIZE) {
            if (!this.moveItemStackTo(stack, AquariumBlockEntity.SIZE, this.slots.size(), false)) {
                return ItemStack.EMPTY;
            }
        } else {
            if (!this.moveItemStackTo(stack, 0, AquariumBlockEntity.SIZE, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.set(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) {
        return access.evaluate((world, pos) -> 
            world.getBlockEntity(pos) instanceof AquariumBlockEntity &&
            player.distanceToSqr(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5) < 64.0, false);
    }
    
    public static class FishInnerSlot extends Slot {
        public FishInnerSlot(Container inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return stack.is(ModTags.Items.FISH);
        }
    }

    public static class RoeInnerSlot extends Slot {
        public RoeInnerSlot(Container inventory, int index, int xPosition, int yPosition) {
            super(inventory, index, xPosition, yPosition);
        }

        @Override
        public boolean mayPlace(ItemStack stack) {
            return false; // Roe slot does not accept any items
        }
    }

}
