package com.plr.signal.common.data;

/*public class CommSettingsCapabilityProvider implements ICapabilityProvider, INBTSerializable<CompoundNBT> {
    private ICommSettingsCapability commSettingsCapability;

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return cap == ModCapability.COMM_SETTINGS_CAPABILITY ? LazyOptional.of(this::getOrCreateCapability).cast() : LazyOptional.empty();
    }

    @Nonnull
    ICommSettingsCapability getOrCreateCapability() {
        return this.commSettingsCapability;
    }

    @Override
    public CompoundNBT serializeNBT() {
        return getOrCreateCapability().serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        getOrCreateCapability().deserializeNBT(nbt);
    }
}

*/