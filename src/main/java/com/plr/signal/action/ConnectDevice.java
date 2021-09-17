package com.plr.signal.action;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;

public class ConnectDevice {
    public static void ConnectDevice(World worldIn, Entity playerIn, int x,int y,int z){
        BlockPos pos = new BlockPos(x,y,z);
        TileEntity tileEntity = worldIn.getBlockEntity(pos);
        double tag = 0;
        if (tileEntity != null) {
            tag = tileEntity.getTileData().getDouble("signalbindable");
        }
        if (tag == 1) {
            worldIn.getBlockState(pos).use(worldIn,(PlayerEntity)playerIn, Hand.MAIN_HAND
                    ,BlockRayTraceResult.miss(new Vector3d(x, y, z), Direction.UP, pos));
        }else {
            playerIn.sendMessage(new TranslationTextComponent("signal.msg.deviceinvalid"), playerIn.getUUID());
        }
    }
}
