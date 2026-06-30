package com.conkcreets.reelresources.customblocks;

import com.conkcreets.reelresources.blockentities.AquariumBlockEntity;
import com.conkcreets.reelresources.registers.ModBlockEntities;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AquariumBlock extends BaseEntityBlock {

    public AquariumBlock(Properties properties) 
    {
        super(properties);
    }

    public static final MapCodec<AquariumBlock> CODEC = simpleCodec(AquariumBlock::new);

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

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) 
    {
        return createTickerHelper(type, ModBlockEntities.AQUARIUM_BLOCK_ENTITY.get(), AquariumBlockEntity::tick);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }
}
