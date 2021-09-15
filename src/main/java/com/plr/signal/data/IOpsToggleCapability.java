package com.plr.signal.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOpsToggleCapability extends INBTSerializable<CompoundNBT> {
    boolean getToggle();
    void setToggle(boolean toggle);
}