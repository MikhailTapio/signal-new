package com.plr.signal.common.item;

import com.plr.signal.common.itemGroup.ModGroup;
import net.minecraft.world.item.Item;

public class Aerial extends Item {
    public Aerial(){
        super (new Properties().tab(ModGroup.itemgroup).stacksTo(1));

    }

}
