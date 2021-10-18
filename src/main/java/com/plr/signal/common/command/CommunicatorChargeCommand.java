package com.plr.signal.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.plr.signal.common.item.Communicator;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;

public class CommunicatorChargeCommand implements Command<CommandSourceStack> {
    public static CommunicatorChargeCommand instance = new CommunicatorChargeCommand();

    @Override
    public int run(CommandContext<CommandSourceStack> context) {
        Player entity = (Player) context.getSource().getEntity();
        if (entity != null && entity.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Communicator.BY_BLOCK) {
            entity.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putInt("currentpower",100000);
        }
        return 0;
    }
}
