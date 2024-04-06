package com.riverfolf.simplestFabricElevator.mixin;

import com.riverfolf.simplestFabricElevator.block.SFEBlocks;
import com.riverfolf.simplestFabricElevator.network.packet.c2s.UseElevatorC2SPacket;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.Input;
import net.minecraft.client.input.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(KeyboardInput.class)
public class KeyboardInputMixin extends Input {
    private int cooldown = 0;
    /**
     * in ticks
     **/
    private final int USAGE_COOLDOWN = 10;

    @Inject(at = @At("TAIL"), method = "tick")
    private void tick(boolean slowDown, float f, CallbackInfo info) {
        if (cooldown > 0) {
            --cooldown;
        }

        if (MinecraftClient.getInstance().player != null) {
            BlockState stepOn = MinecraftClient.getInstance().player.getSteppingBlockState();

            if (!isElevatorBlock(stepOn)) return;

            if (cooldown == 0 && isUsingBlock()) {
                cooldown = USAGE_COOLDOWN;
                UseElevatorC2SPacket.send(jumping);
            }
            preventJumpingAndSneaking();
        }
    }

    private boolean isUsingBlock() {
        return jumping || sneaking;
    }

    private boolean isElevatorBlock(BlockState blockState) {
        return blockState.getBlock() == SFEBlocks.ELEVATOR_BLOCK;
    }

    private void preventJumpingAndSneaking() {
        jumping = false;
        sneaking = false;
    }
}
