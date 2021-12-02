package com.plr.signal.common.data;

import com.plr.signal.common.Utils;
import com.plr.signal.common.block.ChargingStand;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Utils.MOD_ID);
    public static final RegistryObject<Block> chargingstand = BLOCKS.register("charging_stand", ChargingStand::new);
}
