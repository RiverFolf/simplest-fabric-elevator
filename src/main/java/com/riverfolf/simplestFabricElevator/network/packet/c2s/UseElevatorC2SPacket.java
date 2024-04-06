package com.riverfolf.simplestFabricElevator.network.packet.c2s;

import com.riverfolf.simplestFabricElevator.block.SFEBlocks;
import com.riverfolf.simplestFabricElevator.block.custom.ElevatorBlock;
import com.riverfolf.simplestFabricElevator.network.SFEMessages;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.block.BlockState;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;

public class UseElevatorC2SPacket {
    public static void receive(MinecraftServer server, ServerPlayerEntity player, ServerPlayNetworkHandler handler, PacketByteBuf buf, PacketSender responseSender) {
        // Handle packet
        boolean up = buf.readBoolean();

        BlockState steppingBlock = player.getSteppingBlockState();
        if (steppingBlock.getBlock() != SFEBlocks.ELEVATOR_BLOCK)
            return;

        if (up) {
            ((ElevatorBlock) steppingBlock.getBlock()).onJump(player.getEntityWorld(), player.getBlockPos().down(), player);
        } else {
            ((ElevatorBlock) steppingBlock.getBlock()).onSneak(player.getEntityWorld(), player.getBlockPos().down(), player);
        }
    }

    /**
     * @param up up true, down false
     **/
    public static void send(boolean up) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(up);
        ClientPlayNetworking.send(SFEMessages.USE_ELEVATOR_C2S, buf);
    }
}