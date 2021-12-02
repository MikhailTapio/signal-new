package com.plr.signal.common.data;

/*public class CommonEventHandler {
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof Player) {
            event.addCapability(new ResourceLocation(Utils.MOD_ID, "toggle"), new OpsToggleCapabilityProvider());
            event.addCapability(new ResourceLocation(Utils.MOD_ID, "settings"), new SignalSettingsCapabilityProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            LazyOptional<IOpsToggleCapability> oldToggleCap1 = event.getOriginal().getCapability(ModCapability.OPS_TOGGLE_CAPABILITY);
            LazyOptional<ISignalSettingsCapability> oldToggleCap2 = event.getOriginal().getCapability(ModCapability.SIGNAL_SETTINGS_CAPABILITY);
            LazyOptional<IOpsToggleCapability> newToggleCap1 = event.getPlayer().getCapability(ModCapability.OPS_TOGGLE_CAPABILITY);
            LazyOptional<ISignalSettingsCapability> newToggleCap2 = event.getPlayer().getCapability(ModCapability.SIGNAL_SETTINGS_CAPABILITY);
            if (oldToggleCap1.isPresent() && newToggleCap1.isPresent()) {
                newToggleCap1.ifPresent((newCap) -> {
                    oldToggleCap1.ifPresent((oldCap) -> {
                        newCap.deserializeNBT(oldCap.serializeNBT());
                    });
                });
            }
            if (oldToggleCap2.isPresent() && newToggleCap2.isPresent()) {
                newToggleCap2.ifPresent((newCap) -> {
                    oldToggleCap2.ifPresent((oldCap) -> {
                        newCap.deserializeNBT(oldCap.serializeNBT());
                    });
                });
            }
        }
    }
}*/
