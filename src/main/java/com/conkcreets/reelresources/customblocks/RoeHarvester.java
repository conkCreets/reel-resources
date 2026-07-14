package com.conkcreets.reelresources.customblocks;

import com.conkcreets.reelresources.blockentities.RoeHarvesterBlockEntity;
import com.conkcreets.reelresources.registers.ModBlockEntities;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import com.mojang.serialization.MapCodec;

public class RoeHarvester extends BaseEntityBlock {
    
private static final MapCodec<RoeHarvester> CODEC = simpleCodec(RoeHarvester::new);

@Override
public MapCodec<RoeHarvester> codec() {
    return CODEC;
}

    public RoeHarvester(Properties properties) 
    {
        super(properties);
    } 
    
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) 
    {
        return new RoeHarvesterBlockEntity(pos, state);
    }

    @Override
    public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) 
    {
        if (!level.isClientSide())
        {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof RoeHarvesterBlockEntity roeHarvesterBlockEntity) 
            {
                player.openMenu(roeHarvesterBlockEntity);
            }
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type)
    {
        return createTickerHelper(
            type,
            ModBlockEntities.ROE_HARVESTER.get(),
            RoeHarvesterBlockEntity::tick
        );
    }
}


