package com.plr.signal.common.data;

import net.minecraft.nbt.CompoundNBT;

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
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putBoolean("toggle", this.toggle);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.toggle = nbt.getBoolean("toggle");
    }
}
