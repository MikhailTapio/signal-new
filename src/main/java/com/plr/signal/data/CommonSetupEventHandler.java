package com.plr.signal.data;


import net.minecraft.core.Direction;
import net.minecraft.world.level.storage.loot.providers.nbt.NbtProvider;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    /*@SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IOpsToggleCapability.class,
                    new Capability.IStorage<IOpsToggleCapability>() {
                        @Nullable
                        @Override
                        public NbtProvider writeNBT(Capability<IOpsToggleCapability> capability, IOpsToggleCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<IOpsToggleCapability> capability, IOpsToggleCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
            CapabilityManager.INSTANCE.register(
                    ISignalSettingsCapability.class,
                    new Capability.IStorage<ISignalSettingsCapability>() {
                        @Nullable
                        @Override
                        public NbtProvider writeNBT(Capability<ISignalSettingsCapability> capability, ISignalSettingsCapability instance, Direction side) {
                            return null;
                        }

                        @Override
                        public void readNBT(Capability<ISignalSettingsCapability> capability, ISignalSettingsCapability instance, Direction side, INBT nbt) {

                        }
                    },
                    () -> null
            );
        });
    }
*/}
