package com.plr.signal.common.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISignalSettingsCapability extends INBTSerializable<CompoundNBT> {
    long getLocx();
    long getLocy();
    long getLocz();
    int getChannel();
    int getSignalType();
    int getAno();
}
