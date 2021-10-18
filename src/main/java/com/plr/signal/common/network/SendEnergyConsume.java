package com.plr.signal.common.network;

import com.plr.signal.common.item.Communicator;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class SendEnergyConsume {
    private final int energyconsumed;

    public SendEnergyConsume(PacketBuffer buffer){
        energyconsumed = buffer.readInt();
    }

    public SendEnergyConsume(int energyconsumed){
        this.energyconsumed = energyconsumed;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeInt(this.energyconsumed);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if(ctx.get().getSender() != null) {
                ItemStack stack = ctx.get().getSender().getItemInHand(Hand.MAIN_HAND);
                Communicator.consumeEnergy(stack,energyconsumed);
            }});
        ctx.get().setPacketHandled(true);
    }
}
