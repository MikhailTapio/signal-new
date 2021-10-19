package com.plr.signal.client.init;

import com.plr.signal.client.gui.ChargingStandScreen;
import com.plr.signal.common.Utils;
import com.plr.signal.common.block.SContainers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Utils.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SClientRegistryHandler {
    @SubscribeEvent
    public static void setup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(SContainers.containerChargingStand.get(), ChargingStandScreen::new);
        });
    }
}
