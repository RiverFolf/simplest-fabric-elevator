package com.riverfolf.simplestFabricElevator.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElevatorBlock extends Block {
    public ElevatorBlock(Settings settings) {
        super(settings);
    }

    public void onJump(World world, BlockPos pos, PlayerEntity entity) {
        BlockPos lookingPos = pos;
        BlockState found = null;

        while (lookingPos.getY() < world.getTopY()) {
            lookingPos = lookingPos.add(0, 1, 0); //Increment

            BlockState blockState = world.getBlockState(lookingPos);
            if (blockState.getBlock() == this) {
                found = blockState;
                break;
            }
        }

        if (found == null)
            return;

        entity.teleport(entity.getX(), lookingPos.getY() + 1, entity.getZ(), true);
        world.playSound(null, entity.getSteppingPos(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, .5F, world.random.nextFloat() * .6F + .9F);
    }



    public void onSneak(World world, BlockPos pos, PlayerEntity entity) {
        BlockPos lookingPos = pos;
        BlockState found = null;

        while (lookingPos.getY() > world.getBottomY()) {
            lookingPos = lookingPos.add(0, -1, 0); //Increment

            BlockState blockState = world.getBlockState(lookingPos);
            if (blockState.getBlock() == this) {
                found = blockState;
                break;
            }
        }

        if (found == null)
            return;

        entity.teleport(entity.getX(), lookingPos.getY() + 1, entity.getZ(), true);
        world.playSound(null, entity.getSteppingPos(), SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, .5F, world.random.nextFloat() * .6F + .9F);
    }
}
