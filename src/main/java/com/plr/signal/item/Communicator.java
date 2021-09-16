package com.plr.signal.item;


import com.plr.signal.gui.OpenGUIa;
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
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Communicator extends Item {

    public Communicator() {
        super(new Properties().tab(ModGroup.itemgroup).stacksTo(1).durability(0));

    }
    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if ("".equals(playerIn.getUseItem().getOrCreateTag().getString("ownername"))) {
            playerIn.getUseItem().getOrCreateTag().putString("ownername", playerIn.getDisplayName().getString());
        }
        if (worldIn.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        }
        return super.use(worldIn, playerIn, handIn);
    }



    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, CompoundNBT nbt) {
        return new ICapabilityProvider() {
            private final LazyOptional<IEnergyStorage> lazyOptional = LazyOptional.of(() -> new IEnergyStorage() {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(this.getMaxEnergyStored() - energy, maxReceive);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("currentpower", energy + diff);
                    }
                    return diff;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    int energy = this.getEnergyStored();
                    int diff = Math.min(energy, maxExtract);
                    if (!simulate) {
                        stack.getOrCreateTag().putInt("currentpower", energy - diff);
                    }
                    return diff;
                }

                @Override
                public int getEnergyStored() {
                    if (stack.hasTag()) {
                        int energy = Objects.requireNonNull(stack.getTag()).getInt("currentpower");
                        return Math.max(0, Math.min(this.getMaxEnergyStored(), energy));
                    }
                    return 0;
                }

                @Override
                public int getMaxEnergyStored() {
                    return 100000;
                }

                @Override
                public boolean canExtract() {
                    return false;
                }

                @Override
                public boolean canReceive() {
                    return true;
                }
            });

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, Direction side) {
                boolean isEnergy = Objects.equals(cap, CapabilityEnergy.ENERGY);
                return isEnergy ? this.lazyOptional.cast() : LazyOptional.empty();
            }
        };
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World worldIn, Entity playerIn, int slot, boolean selected) {
        super.inventoryTick(itemstack, worldIn, playerIn, slot, selected);

        if (playerIn.getPersistentData().getBoolean("toggle")) {
            long x = Math.round(playerIn.getX());
            long y = Math.round(playerIn.getY());
            long z = Math.round(playerIn.getZ());
            {
                playerIn.getPersistentData().putLong("locx", x);
                playerIn.getPersistentData().putLong("locy", y);
                playerIn.getPersistentData().putLong("locz", z);
            }
        }
        if (worldIn.isDay() && itemstack.getOrCreateTag().getInt("currentpower") < 100000){
            {itemstack.getOrCreateTag().putInt("currentpower", itemstack.getOrCreateTag().getInt("currentpower") + 10);}
        }
    }
}
