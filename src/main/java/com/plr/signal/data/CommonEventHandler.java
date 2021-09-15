package com.plr.signal.data;

import com.plr.signal.Utils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CommonEventHandler {
    @SubscribeEvent
    public static void onAttachCapabilityEvent(AttachCapabilitiesEvent<Entity> event) {
        Entity entity = event.getObject();
        if (entity instanceof PlayerEntity) {
            event.addCapability(new ResourceLocation(Utils.MOD_ID, "toggle"), new OpsToggleCapabilityProvider());
        }
    }
    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (!event.isWasDeath()) {
            LazyOptional<IOpsToggleCapability> oldToggleCap = event.getOriginal().getCapability(ModCapability.OPS_TOGGLE_CAPABILITY);
            LazyOptional<IOpsToggleCapability> newToggleCap = event.getPlayer().getCapability(ModCapability.OPS_TOGGLE_CAPABILITY);
            if (oldToggleCap.isPresent() && newToggleCap.isPresent()) {
                newToggleCap.ifPresent((newCap) -> {
                    oldToggleCap.ifPresent((oldCap) -> {
                        newCap.deserializeNBT(oldCap.serializeNBT());
                    });
                });
            }
        }
    }
}
