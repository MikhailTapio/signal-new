package com.plr.signal.common.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface IOpsToggleCapability extends INBTSerializable<CompoundTag> {
    boolean getToggle();
    void setToggle(boolean toggle);
}
