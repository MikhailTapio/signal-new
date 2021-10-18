package com.plr.signal.common.data;

import net.minecraft.nbt.CompoundNBT;

public class SignalSettingsCapability implements ISignalSettingsCapability{
    private long Locx;
    private long Locy;
    private long Locz;
    private long Bindx;
    private long Bindy;
    private long Bindz;
    private int Bound;
    private int Binding;
    private String BindDim;
    private int Channel;
    private int SignalType;
    private int Ano;

    public SignalSettingsCapability(long Locx,long Locy,long Locz,
                                    long Bindx,long Bindy,long Bindz,
                                    int Bound,int Binding,String BindDim,
                                    int Channel,int SignalType,int Ano)
    {
        this.Locx = Locx;
        this.Locy = Locy;
        this.Locz = Locz;
        this.Bindx = Bindx;
        this.Bindy = Bindy;
        this.Bindz = Bindz;
        this.Channel = Channel;
        this.SignalType = SignalType;
        this.Ano = Ano;
        this.Bound = Bound;
        this.Binding = Binding;
        this.BindDim = BindDim;
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
    public long getBindx() {
        return Bindx;
    }
    @Override
    public long getBindy() {
        return Bindy;
    }
    @Override
    public long getBindz() {
        return Bindz;
    }
    @Override
    public int getBound() {
        return Bound;
    }
    @Override
    public int getBinding() {
        return Binding;
    }
    @Override
    public String getBindDim(){ return BindDim; }
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
        compoundNBT.putLong("bindx", this.Bindx);
        compoundNBT.putLong("bindy", this.Bindy);
        compoundNBT.putLong("bindz", this.Bindz);
        compoundNBT.putInt("bound", this.Bound);
        compoundNBT.putInt("binding", this.Binding);
        compoundNBT.putString("binddim", this.BindDim);
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
        this.Bindx = nbt.getLong("bindx");
        this.Bindy = nbt.getLong("bindy");
        this.Bindz = nbt.getLong("bindz");
        this.Bound = nbt.getInt("bound");
        this.Binding = nbt.getInt("binding");
        this.BindDim = nbt.getString("binddim");
        this.Channel = nbt.getInt("channel");
        this.SignalType = nbt.getInt("type");
        this.Ano = nbt.getInt("ano");
    }
}

