package com.plr.signal.item;

import com.plr.signal.gui.OpenGUI;
import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Communicator extends Item {

    public Communicator() {
        super(new Properties().tab(ModGroup.itemgroup).stacksTo(1).durability(0));
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUI::new);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World worldIn, Entity playerIn, int slot, boolean selected) {
        if(itemstack.getOrCreateTag().getBoolean("opson")){
        long x = Math.round(playerIn.getX());
        long y = Math.round(playerIn.getY());
        long z = Math.round(playerIn.getZ());
            {
                itemstack.getOrCreateTag().putLong("locx", x);
                itemstack.getOrCreateTag().putLong("locy", y);
                itemstack.getOrCreateTag().putLong("locz", z);
            }
        }
    }

}
