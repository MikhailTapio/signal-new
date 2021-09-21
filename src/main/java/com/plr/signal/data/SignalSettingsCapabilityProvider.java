package com.plr.signal.data;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SignalSettingsCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private ISignalSettingsCapability signalSettingsCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.SIGNAL_SETTINGS_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    ISignalSettingsCapability getOrCreateCapability() {
        return this.signalSettingsCapability;
    }

    @Override
    public CompoundTag serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}

