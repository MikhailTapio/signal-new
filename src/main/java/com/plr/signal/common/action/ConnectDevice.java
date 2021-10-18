package com.plr.signal.common.action;

import com.plr.signal.common.network.Networking;
import com.plr.signal.common.network.RemoteControlPacket;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ConnectDevice {
    public static void ConnectDevice(World worldIn, Entity playerIn, long x,long y,long z){
        BlockPos pos = new BlockPos(x,y,z);
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        double tag = 0;
        if (tileEntity != null) {
            tag = tileEntity.getTileData().getDouble("signalbindable");
        }
        if (tag == 1) {
            Networking.INSTANCE.sendToServer(new RemoteControlPacket(pos));
        }else {
            playerIn.sendMessage(new TranslationTextComponent("signal.msg.deviceinvalid"), playerIn.getUUID());
        }
    }
}
