package com.conkcreets.reelresources.customblocks;

import com.conkcreets.reelresources.blockentities.AquariumBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AquariumBlock extends Block implements EntityBlock {

    public AquariumBlock(Properties properties) 
    {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) 
    {
        return new AquariumBlockEntity(pos, state);
    }
    
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) 
    {
        if (!level.isClientSide())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AquariumBlockEntity aquariumBlockEntity) 
            {
                player.openMenu(aquariumBlockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
