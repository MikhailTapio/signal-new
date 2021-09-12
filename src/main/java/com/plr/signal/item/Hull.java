package com.plr.signal.item;

import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.item.Item;

public class Hull extends Item {
    public Hull(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));
    }
}
