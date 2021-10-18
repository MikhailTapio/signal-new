package com.plr.signal.common.item;

import com.plr.signal.common.itemGroup.ModGroup;
import net.minecraft.world.item.Item;

public class Mainboard extends Item {
    public Mainboard(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));
    }
}
