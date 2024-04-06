package com.riverfolf.simplestFabricElevator.network;

import com.riverfolf.simplestFabricElevator.SimplestFabricElevatorMod;
import com.riverfolf.simplestFabricElevator.network.packet.c2s.UseElevatorC2SPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class SFEMessages {
    public static final Identifier USE_ELEVATOR_C2S = new Identifier(SimplestFabricElevatorMod.MOD_ID, "use_elevator_c2s");

    public static void registerC2SPackets(){
        ServerPlayNetworking.registerGlobalReceiver(USE_ELEVATOR_C2S, UseElevatorC2SPacket::receive);
    }
}
