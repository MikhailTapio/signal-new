package com.plr.signal.action;

import com.plr.signal.Utils;
import com.plr.signal.item.Communicator;
import net.minecraft.network.chat.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.fmllegacy.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SendSignal {
    public static void sendSignal(ItemStack itemstack, Level worldIn, Entity playerIn){
        SoundEvent soundEvent = null;
        String stype = null;
        String sender;
        long actualX = playerIn.getPersistentData().getLong("locx");
        long actualY = playerIn.getPersistentData().getLong("locy");
        long actualZ = playerIn.getPersistentData().getLong("locz");
        if(playerIn.getPersistentData().getInt("ano")==0){
            sender = playerIn.getDisplayName().getString();
        }else {
            sender = new TranslatableComponent("signal.msg.anonymousplayer").getString();
        }
        switch (playerIn.getPersistentData().getInt("type")){
            case 0:
                stype = "distress";
                soundEvent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "emergency"));
                break;
            case 1:
                stype = "need.resource";
                soundEvent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "default"));
                break;
            case 2:
                stype = "need.manpower";
                soundEvent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "default"));
                break;
            case 3:
                stype = "found.threat";
                soundEvent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "alert"));
                break;
            case 4:
                stype = "found.resource";
                soundEvent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "default"));
                break;
            default:
        }
        switch(playerIn.getPersistentData().getInt("channel")){
            case 0:
                if(itemstack.getOrCreateTag().getInt("currentpower")>=20000){
                    {
                        List<Entity> entfound = worldIn.getEntitiesOfClass(Entity.class,
                                new AABB(playerIn.getX() - 1000, playerIn.getY() - 1000, playerIn.getZ() - 1000, playerIn.getX() + 1000, playerIn.getY() + 1000, playerIn.getZ() + (2000 / 2d)))
                                .stream().sorted(new Object() {
                                    Comparator<Entity> compareDistOf(double ax, double ay, double az) {
                                        return Comparator.comparing((Function<Entity, Double>) (entcnd -> entcnd.distanceToSqr(ax, ay, az)));
                                    }
                                }.compareDistOf(playerIn.getX(), playerIn.getY(), playerIn.getZ())).collect(Collectors.toList());
                        for (Entity entityiterator : entfound) {
                            if ((entityiterator instanceof Player)) {
                                ((Player) entityiterator).displayClientMessage(new TextComponent(
                                        sender + (new TranslatableComponent("signal.msg.1").getString())
                                                + (new TranslatableComponent("signal.msg.type." + stype).getString())
                                                + (new TranslatableComponent("signal.msg.locis").getString())
                                                + "(" + actualX + "," + actualY + "," + actualZ + ")."
                                ) , false);
                                ((Player) entityiterator).playSound(soundEvent,1.0F,1.0F);
                            }
                        }
                    }
                    Communicator.consumeEnergy(itemstack,20000);
                }else{playerIn.sendMessage(new TranslatableComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            case 1:
                if(itemstack.getOrCreateTag().getInt("currentpower")>=60000){
                    List<? extends Player> _players = new ArrayList<>(worldIn.players());
                    for (Entity entityiterator : _players) {
                        if ((entityiterator instanceof Player)) {
                            ((Player) entityiterator).displayClientMessage(new TextComponent(
                                    sender + (new TranslatableComponent("signal.msg.2").getString())
                                            + (new TranslatableComponent("signal.msg.type." + stype).getString())
                                            + (new TranslatableComponent("signal.msg.locis").getString())
                                            + "("+actualX+","+actualY+","+actualZ+")."
                            ),false);
                            ((Player) entityiterator).playSound(soundEvent,1.0F,1.0F);
                        }}
                    Communicator.consumeEnergy(itemstack,60000);
                }else{playerIn.sendMessage(new TranslatableComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            case 2:
                if(itemstack.getOrCreateTag().getInt("currentpower")>=90000) {
                    //if (!worldIn.isClientSide()) {
                    MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

                    //if (server != null){
                    String dim = null;
                    if ("ResourceKey[minecraft:dimension / minecraft:overworld]".equals(playerIn.level.dimension().toString())) {
                        dim = new TranslatableComponent("signal.msg.dim.world").getString();
                    } else if ("ResourceKey[minecraft:dimension / minecraft:the_nether])".equals(playerIn.level.dimension().toString())) {
                        dim = new TranslatableComponent("signal.msg.dim.nether").getString();
                    } else if ("ResourceKey[minecraft:dimension / minecraft:the_end])".equals(playerIn.level.dimension().toString())) {
                        dim = new TranslatableComponent("signal.msg.dim.end").getString();
                    } else {
                        dim = new TranslatableComponent("signal.msg.dim.other").getString();
                    }
                    server.getPlayerList().broadcastMessage(new TextComponent(
                            sender + (new TranslatableComponent("signal.msg.3").getString())
                                    + (new TranslatableComponent("signal.msg.type." + stype).getString())
                                    + (new TranslatableComponent("signal.msg.locis").getString())
                                    + "(" + actualX + "," + actualY + "," + actualZ + ")."
                                    + (new TranslatableComponent("signal.msg.dimis").getString())
                                    + dim
                    ), ChatType.SYSTEM, playerIn.getUUID());
                    List<? extends Player> _players = new ArrayList<>(worldIn.players());
                    for (Entity entityiterator : _players) {
                        if ((entityiterator instanceof Player)) {
                            ((Player) entityiterator).playSound(soundEvent, 1.0F, 1.0F);
                        }
                    }
                    Communicator.consumeEnergy(itemstack,90000);
                }
                else{playerIn.sendMessage(new TranslatableComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            default:
        }
    }

}