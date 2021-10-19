package com.plr.signal.common.block;

import com.mojang.datafixers.DSL;
import com.plr.signal.common.data.BlockRegistry;
import com.plr.signal.common.data.ItemRegistry;
import com.plr.signal.common.util.energystorage.SEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ObjectHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

import static com.plr.signal.common.block.ChargingStand.CHARGING;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ChargingStandBlockEntity extends BlockEntity implements MenuProvider {
    public static final String NAME = "signal:charging_stand";
    @ObjectHolder(NAME)
    public static BlockEntityType<ChargingStandBlockEntity> BLOCK_ENTITY_TYPE;

    private final ItemStackHandler itemHandler = createHandler();
    private final SEnergyStorage energyStorage = createEnergy();

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    @SubscribeEvent
    public static void onRegisterBlockEntityType(@Nonnull RegistryEvent.Register<BlockEntityType<?>> event) {
        event.getRegistry().register(BlockEntityType.Builder.
                of(ChargingStandBlockEntity::new, BlockRegistry.chargingstand.get()).build(DSL.remainderType()).setRegistryName(NAME));
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("");
    }

    public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity) {

        if (level != null) {
            return new ChargingStandContainer(windowId, level, worldPosition, playerInventory, playerEntity);
        }
        return null;
    }

    //Disable capabilities when block is removed
    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }
    //Block's energy generation and sound playing

    public void tickServer(BlockState state) {
        boolean canGenerateEnergy = level != null && level.canSeeSky(worldPosition.above());
        if(canGenerateEnergy) {
            energyStorage.addEnergy(10);
            setChanged();
        }


        //Currently no extra conditions for activating the generator.
        BlockState blockState = null;
        ItemStack stack = itemHandler.getStackInSlot(0);
        if (level != null) {
            blockState = level.getBlockState(worldPosition);
        }
        if (blockState != null)
        {boolean charging = stack.is(ItemRegistry.communicator.get());
            level.setBlock(worldPosition, blockState.setValue(CHARGING, charging),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);}


        if(stack.getCapability(CapabilityEnergy.ENERGY).isPresent())
        {
            AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
            boolean doContinue = stack.getCapability(CapabilityEnergy.ENERGY).map(handler ->
                    {if (handler.canReceive()) {
                        int received = handler.receiveEnergy(Math.min(capacity.get(), 100), false);
                        capacity.addAndGet(-received);
                        energyStorage.consumeEnergy(received);
                        setChanged();
                        return capacity.get() > 0;
                    } else {
                        return true;
                    }
                    }
            ).orElse(true);
            if (!doContinue) {
                return;
            }
        }
    }



    public ChargingStandBlockEntity(BlockPos pos, BlockState state)
    {
        super(BLOCK_ENTITY_TYPE,pos,state);
    }

    //NBT saving and loading
    @Override
    public CompoundTag save(@Nonnull  CompoundTag tag) {
        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());

        return super.save(tag);
    }

    @Override
    public void load(@Nonnull CompoundTag tag) {
        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.get("energy"));
        super.load(tag);
    }


    private SEnergyStorage createEnergy() {
        return new SEnergyStorage(100000, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    //ItemStack Handler
    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.is(ItemRegistry.communicator.get())) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}
