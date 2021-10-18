package com.plr.signal.common.data;

/*public class CommSettingsCapability implements ICommSettingsCapability{
    private long Bindx;
    private long Bindy;
    private long Bindz;
    private int Binding;
    private int Bound;
    private String BindDim;

    public CommSettingsCapability(long Bindx, long Bindy, long Bindz, int Binding, int Bound, String BindDim){
        this.Bindx = Bindx;
        this.Bindy = Bindy;
        this.Bindz = Bindz;
        this.Binding = Binding;
        this.Bound = Bound;
        this.BindDim = BindDim;
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
    public int getBinding() {
        return Binding;
    }
    @Override
    public int getBound() {
        return Bound;
    }
    @Override
    public String getBindDim() {
        return BindDim;
    }



    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT compoundNBT = new CompoundNBT();
        compoundNBT.putLong("bindx", this.Bindx);
        compoundNBT.putLong("bindy", this.Bindy);
        compoundNBT.putLong("bindz", this.Bindz);
        compoundNBT.putInt("binding", this.Binding);
        compoundNBT.putInt("bound", this.Bound);
        compoundNBT.putString("binddim", this.BindDim);
        return compoundNBT;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.Bindx = nbt.getLong("bindx");
        this.Bindy = nbt.getLong("bindy");
        this.Bindz = nbt.getLong("bindz");
        this.Binding = nbt.getInt("binding");
        this.Bound = nbt.getInt("bound");
        this.BindDim = nbt.getString("binddim");
    }
}
*/
