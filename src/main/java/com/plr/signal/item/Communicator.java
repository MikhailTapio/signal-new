package com.plr.signal.item;

import com.plr.signal.gui.OpenGUI;
import com.plr.signal.itemGroup.ModGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class Communicator extends Item {
    public Communicator() {
        super(new Properties().tab(ModGroup.itemgroup).stacksTo(1).durability(0));
        long x;
        long y;
        long z;
    }

    @Override
    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isClientSide) {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUI::new);
        }
        return super.use(worldIn, playerIn, handIn);
    }

    @Override
    public void inventoryTick(ItemStack itemstack, World worldIn, Entity playerIn, int slot, boolean selected) {
        super.inventoryTick(itemstack, worldIn, playerIn, slot, selected);

    }
}
