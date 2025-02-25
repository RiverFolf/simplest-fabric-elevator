package com.riverfolf.simplestFabricElevator.block;

import com.riverfolf.simplestFabricElevator.SimplestFabricElevatorMod;
import com.riverfolf.simplestFabricElevator.block.custom.ElevatorBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SFEBlocks {
    public static final Block ELEVATOR_BLOCK = registerBlock(
            "elevator_block",
            new ElevatorBlock(FabricBlockSettings
                    .of(Material.STONE)
                    .strength(1f)),
            ItemGroup.REDSTONE);

    private static Block registerBlock(String name, Block block, ItemGroup group) {
        registerBlockItem(name, block, group);
        return Registry.register(Registry.BLOCK, new Identifier(SimplestFabricElevatorMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block, ItemGroup group) {
        Registry.register(Registry.ITEM, new Identifier(SimplestFabricElevatorMod.MOD_ID, name), new BlockItem(block, new FabricItemSettings().group(group)));
    }


    public static void registerBlocks() {
        SimplestFabricElevatorMod.LOGGER.debug("Registering mod blocks for " + SimplestFabricElevatorMod.MOD_ID);
    }
}
