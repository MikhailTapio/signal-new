package com.plr.signal.common.action;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class ConnectDevice {
    public static void ConnectDevice(Level worldIn, Entity playerIn, double x, double y, double z){
        BlockPos pos = new BlockPos(x,y,z);
        BlockEntity tileEntity = worldIn.getBlockEntity(pos);
        double tag = 0;
        if (tileEntity != null) {
            tag = tileEntity.getTileData().getDouble("signalbindable");
        }
        if (tag == 1) {
            worldIn.getBlockState(pos).use(worldIn,(Player) playerIn, InteractionHand.MAIN_HAND
                    , BlockHitResult.miss(new Vec3(pos.getX(), pos.getY(), pos.getZ()), Direction.UP, pos));
        }else {
            playerIn.sendMessage(new TranslatableComponent("signal.msg.deviceinvalid"), playerIn.getUUID());
        }
    }
}
