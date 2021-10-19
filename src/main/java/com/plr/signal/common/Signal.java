package com.plr.signal.common;

import com.plr.signal.common.block.SContainerHandler;
import com.plr.signal.common.data.BlockRegistry;
import com.plr.signal.common.data.ItemRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Utils.MOD_ID)
public class Signal {
    public Signal(){
        ItemRegistry.ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        BlockRegistry.BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        SContainerHandler.register();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
