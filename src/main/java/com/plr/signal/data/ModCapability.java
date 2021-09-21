package com.plr.signal.data;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class ModCapability {
    @CapabilityInject(IOpsToggleCapability.class)
    public static Capability<IOpsToggleCapability> OPS_TOGGLE_CAPABILITY;
    @CapabilityInject(ISignalSettingsCapability.class)
    public static Capability<ISignalSettingsCapability> SIGNAL_SETTINGS_CAPABILITY;

}
