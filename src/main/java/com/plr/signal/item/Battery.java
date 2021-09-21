package com.plr.signal.item;

import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.world.item.Item;

public class Battery extends Item {
    public Battery(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));
    }
}
