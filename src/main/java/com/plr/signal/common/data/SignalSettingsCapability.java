package com.plr.signal.common.data;

import net.minecraft.nbt.CompoundNBT;

public class SignalSettingsCapability implements ISignalSettingsCapability{
    private long Locx;
    private long Locy;
    private long Locz;
    private int Channel;
    private int SignalType;
    private int Ano;

    public SignalSettingsCapability(long Locx,long Locy,long Locz){
        this.Locx = Locx;
        this.Locy = Locy;
        this.Locz = Locz;
    }
    public SignalSettingsCapability(int Channel,int SignalType,int Ano){
        this.Channel = Channel;
        this.SignalType = SignalType;
        this.Ano = Ano;
    }

    @Override
    public long getLocx() {
        return Locx;
    }
    @Override
    public long getLocy() {
        return Locy;
    }
    @Override
    public long getLocz() {
        return Locz;
    }
    @Override
    public int getChannel() {
        return Channel;
    }
    @Override
    public int getSignalType() {
        return SignalType;
    }
    @Override
    public int getAno() {
        return Ano;
    }



    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putLong("locx", this.Locx);
        compoundNBT.putLong("locy", this.Locy);
        compoundNBT.putLong("locz", this.Locz);
        compoundNBT.putInt("channel", this.Channel);
        compoundNBT.putInt("type", this.SignalType);
        compoundNBT.putInt("ano", this.Ano);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.Locx = nbt.getLong("locx");
        this.Locy = nbt.getLong("locy");
        this.Locz = nbt.getLong("locz");
        this.Channel = nbt.getInt("channel");
        this.SignalType = nbt.getInt("type");
        this.Ano = nbt.getInt("ano");
    }
}

