package com.plr.signal.common.block;

import com.plr.signal.common.Utils;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SContainerHandler {
    public static final DeferredRegister<MenuType<?>> Containers = DeferredRegister.create(ForgeRegistries.CONTAINERS, Utils.MOD_ID);
    public static void register()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        SContainers.register();
        Containers.register(eventBus);
    }
}
