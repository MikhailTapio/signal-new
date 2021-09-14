package com.plr.signal.item;

import com.plr.signal.gui.OpenGUIa;
import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.command.arguments.NBTCompoundTagArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;

public class Communicator extends Item {

    public Communicator() {
        super(new Properties().tab(ModGroup.itemgroup).stacksTo(1).durability(0));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if ("".equals(playerIn.getUseItem().getOrCreateTag().getString("ownername"))){
            playerIn.getUseItem().getOrCreateTag().putString("ownername",playerIn.getDisplayName().toString());
        }
        if (worldIn.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World worldIn, Entity playerIn, int slot, boolean selected) {
            if(itemstack.getOrCreateTag().getBoolean("opson"))
            {
        long x = Math.round(playerIn.getX());
        long y = Math.round(playerIn.getY());
        long z = Math.round(playerIn.getZ());
            {
                itemstack.getOrCreateTag().putLong("locx", x);
                itemstack.getOrCreateTag().putLong("locy", y);
                itemstack.getOrCreateTag().putLong("locz", z);
            }}
        if(itemstack.getOrCreateTag().getLong("currentpower") < 1000){
            {
                itemstack.getOrCreateTag().putLong("currentpower", itemstack.getOrCreateTag().getLong("currentpower") + 1);
            }
        }}
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
            if (stack.getTag() == null) {
                stack.setTag(new CompoundNBT());
            }
        return super.initCapabilities(stack, nbt);
    }
}
