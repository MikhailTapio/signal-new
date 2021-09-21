package com.plr.signal.data;


import net.minecraft.nbt.CompoundTag;

public class OpsToggleCapability implements IOpsToggleCapability{
    private boolean toggle;

    public OpsToggleCapability(boolean toggle){
        this.toggle = toggle;

    }

    @Override
    public boolean getToggle() {
        return toggle;
    }

    @Override
    public void setToggle(boolean toggle){
        this.toggle = toggle;
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag compoundNBT = new CompoundTag();
        compoundNBT.putBoolean("toggle", this.toggle);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.toggle = nbt.getBoolean("toggle");
    }
}
