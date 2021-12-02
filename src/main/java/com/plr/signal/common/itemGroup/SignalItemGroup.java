package com.plr.signal.common.itemGroup;

import com.plr.signal.common.data.ItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class SignalItemGroup extends CreativeModeTab {
    public SignalItemGroup(){
        super("signal_group");
    }

    @Nonnull
    @Override
    public ItemStack makeIcon() {
        return new ItemStack(ItemRegistry.communicator.get());
    }


}
