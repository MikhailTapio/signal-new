package com.plr.signal.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fmllegacy.RegistryObject;

public class SContainers {
    public static RegistryObject<MenuType<ChargingStandContainer>> containerChargingStand;

    public static void register(){

        containerChargingStand = SContainerHandler.Containers.register("charging_stand", () -> IForgeContainerType.create((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            Level world = inv.player.getCommandSenderWorld();
            return new ChargingStandContainer(windowId, world, pos, inv, inv.player);
        }));
    }
}
