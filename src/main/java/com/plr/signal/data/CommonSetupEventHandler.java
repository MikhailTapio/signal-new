package com.plr.signal.data;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IOpsToggleCapability.class,
                    new Capability.IStorage<IOpsToggleCapability>() {
                        @Nullable
                        @Override
                        public INBT writeNBT(Capability<IOpsToggleCapability> capability, IOpsToggleCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IOpsToggleCapability> capability, IOpsToggleCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }
}
