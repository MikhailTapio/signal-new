package com.plr.signal.common.item;


import com.plr.signal.client.gui.OpenGUIa;
import com.plr.signal.common.itemGroup.ModGroup;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class Communicator extends Item {

    public Communicator() {
        super(new Properties().tab(ModGroup.itemgroup).stacksTo(1).durability(0));

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if(!(playerIn.isCrouching())){
        if ("".equals(playerIn.getUseItem().getOrCreateTag().getString("ownername"))) {
            playerIn.getUseItem().getOrCreateTag().putString("ownername", playerIn.getDisplayName().getString());
        }
        if (worldIn.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        }}
        return super.use(worldIn, playerIn, handIn);
    }

    /*@Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        ActionResultType retval = super.onItemUseFirst(stack, context);
        World world = context.getLevel();
        BlockPos pos = context.getClickedPos();
        PlayerEntity entity = context.getPlayer();
        ItemStack itemstack = context.getItemInHand();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        {
            TileEntity tileEntity = world.getBlockEntity(pos);
            double bindtag = 0;
            if (tileEntity != null) {
                bindtag = tileEntity.getTileData().getDouble("signalbindable");
            }
            if (entity != null && (entity.isCrouching()) && (itemstack.getOrCreateTag().getInt("binding") == 1) && (bindtag == 1)) {
                itemstack.getOrCreateTag().putDouble("bindx", x);
                itemstack.getOrCreateTag().putDouble("bindy", y);
                itemstack.getOrCreateTag().putDouble("bindz", z);
                itemstack.getOrCreateTag().putString("binddim", world.dimension().toString());
                itemstack.getOrCreateTag().putInt("bound", 1);
                itemstack.getOrCreateTag().putInt("binding", 0);
                entity.playSound(new SoundEvent(new ResourceLocation(Utils.MOD_ID, "devicebound")), 1.0F, 1.0F);
                entity.sendMessage(new TranslationTextComponent("signal.msg.devicebound"), entity.getUUID());
            }
        }
        return retval;
    }*/



        @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, CompoundTag nbt) {
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
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> list, TooltipFlag flag){
        if (stack.getTag() != null) {
            list.add(new TextComponent(new TranslatableComponent("signal.tooltip.energy").getString() + stack.getTag().getInt("currentpower") + " / 100000" ));
        }
    }

    @Override
    public void inventoryTick(ItemStack itemstack, Level worldIn, Entity playerIn, int slot, boolean selected) {
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
    }

    public static void consumeEnergy(@Nonnull ItemStack stack, int power)
    {
        stack.getCapability(CapabilityEnergy.ENERGY).ifPresent(e -> {
            e.extractEnergy(power,false);
        }
        );
    }
}
