package com.plr.signal.common.item;

import com.plr.signal.common.itemGroup.ModGroup;
import net.minecraft.world.item.Item;

public class Hull extends Item {
    public Hull(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));
    }
}
