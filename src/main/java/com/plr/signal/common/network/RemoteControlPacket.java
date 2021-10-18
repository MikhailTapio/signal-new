package com.plr.signal.common.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class RemoteControlPacket {
    private final BlockPos pos;

    public RemoteControlPacket(PacketBuffer buffer){
        pos = buffer.readBlockPos();
    }

    public RemoteControlPacket(BlockPos pos){
        this.pos = pos;
    }

    public void toBytes(PacketBuffer buf) {
        buf.writeBlockPos(this.pos);
    }

    public void handler(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayerEntity player = ctx.get().getSender();
            World worldIn = player.getLevel();
            worldIn.getBlockState(pos).use(worldIn,(PlayerEntity)player, Hand.MAIN_HAND
                    , BlockRayTraceResult.miss(new Vector3d(pos.getX(), pos.getY(), pos.getZ()), Direction.UP, pos));
        });
        ctx.get().setPacketHandled(true);
    }
}
