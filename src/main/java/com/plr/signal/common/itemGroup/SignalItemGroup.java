package com.plr.signal.common.itemGroup;

import com.plr.signal.common.data.ItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class SignalItemGroup extends ItemGroup {
    public SignalItemGroup(){
        super("signal_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.communicator.get());
    }


}
