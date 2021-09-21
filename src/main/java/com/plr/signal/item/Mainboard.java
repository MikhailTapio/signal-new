package com.plr.signal.item;

import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.world.item.Item;

public class Mainboard extends Item {
    public Mainboard(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));
    }
}
