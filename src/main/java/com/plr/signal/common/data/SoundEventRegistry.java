package com.plr.signal.common.data;

import com.plr.signal.common.Utils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventRegistry {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Utils.MOD_ID);
    public static final RegistryObject<SoundEvent> OPS_ON =
            SOUNDS.register("opson", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "opson")));
    public static final RegistryObject<SoundEvent> OPS_OFF =
            SOUNDS.register("opsoff", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "opsoff")));
    public static final RegistryObject<SoundEvent> EMERGENCY =
            SOUNDS.register("emergency", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "emergency")));
    public static final RegistryObject<SoundEvent> ALERT =
            SOUNDS.register("alert", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "alert")));
    public static final RegistryObject<SoundEvent> DEFAULT =
            SOUNDS.register("default", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "default")));
    public static final RegistryObject<SoundEvent> DEVICE_BOUND =
            SOUNDS.register("devicebound", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "devicebound")));
    public static final RegistryObject<SoundEvent> UNBOUND =
            SOUNDS.register("unbound", () -> new SoundEvent(new ResourceLocation(Utils.MOD_ID, "unbound")));
}
