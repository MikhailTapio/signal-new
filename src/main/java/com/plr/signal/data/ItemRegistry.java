package com.plr.signal.data;

import com.plr.signal.Utils;
import com.plr.signal.item.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Utils.MOD_ID);
    public static final RegistryObject<Item> communicator = ITEMS.register("communicator", Communicator::new);
    public static final RegistryObject<Item> hull = ITEMS.register("hull", Hull::new);
    public static final RegistryObject<Item> aerial = ITEMS.register("aerial", Aerial::new);
    public static final RegistryObject<Item> mainboard = ITEMS.register("mainboard", Mainboard::new);
    public static final RegistryObject<Item> battery = ITEMS.register("battery", Battery::new);
}
