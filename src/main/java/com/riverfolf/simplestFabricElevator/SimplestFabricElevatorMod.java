package com.riverfolf.simplestFabricElevator;

import com.riverfolf.simplestFabricElevator.block.SFEBlocks;
import com.riverfolf.simplestFabricElevator.network.SFEMessages;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplestFabricElevatorMod  implements ModInitializer {
    public static final String MOD_ID = "simplest-fabric-elevator";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        SFEBlocks.registerBlocks();
        SFEMessages.registerC2SPackets();
    }
}