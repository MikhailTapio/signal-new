package com.plr.signal.common.data;


import com.plr.signal.common.network.Networking;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetupEventHandler {
    @SubscribeEvent
    public static void onSetupEvent(FMLCommonSetupEvent event) {
        event.enqueueWork(Networking::registerMessage);
        /*event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(
                    IOpsToggleCapability.class,
                    new Capability<IOpsToggleCapability>() {
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
                    new Capability<ISignalSettingsCapability>() {
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
        });*/
    }
}

