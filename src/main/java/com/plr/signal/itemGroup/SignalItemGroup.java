package com.plr.signal.itemGroup;

import com.plr.signal.data.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SignalItemGroup extends CreativeModeTab {
    public SignalItemGroup(){
        super("signal_group");
    }

    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.communicator.get());
    }


}
