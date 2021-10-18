package com.plr.signal.common.network;

import com.plr.signal.common.item.Communicator;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fmllegacy.network.NetworkEvent;

import java.util.function.Supplier;

public class SendEnergyConsume {
    private final int energyconsumed;

    public SendEnergyConsume(FriendlyByteBuf buffer){
        energyconsumed = buffer.readInt();
    }

    public SendEnergyConsume(int energyconsumed){
        this.energyconsumed = energyconsumed;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.energyconsumed);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(ctx.get().getSender() != null) {
                ItemStack stack = ctx.get().getSender().getItemInHand(InteractionHand.MAIN_HAND);
                Communicator.consumeEnergy(stack,energyconsumed);
            }});
        ctx.get().setPacketHandled(true);
    }
}
