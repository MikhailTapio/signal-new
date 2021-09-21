package com.plr.signal.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ISignalSettingsCapability extends INBTSerializable<CompoundTag> {
    long getLocx();
    long getLocy();
    long getLocz();
    int getChannel();
    int getSignalType();
    int getAno();
}
