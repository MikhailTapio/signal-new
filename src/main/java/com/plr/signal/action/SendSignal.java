package com.plr.signal.action;

import com.plr.signal.Utils;
import com.plr.signal.data.SoundEventRegistry;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SendSignal {
    public static void sendSignal(ItemStack itemstack, World worldIn, Entity playerIn){
        SoundEvent soundEvent = null;
        String stype = null;
        String sender;
        long actualX = playerIn.getPersistentData().getLong("locx");
        long actualY = playerIn.getPersistentData().getLong("locy");
        long actualZ = playerIn.getPersistentData().getLong("locz");
        if(playerIn.getPersistentData().getInt("ano")==0){
            sender = playerIn.getDisplayName().getString();
        }else {
            sender = new TranslationTextComponent("signal.msg.anonymousplayer").getString();
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
                                new AxisAlignedBB(playerIn.getX() - 1000, playerIn.getY() - 1000, playerIn.getZ() - 1000, playerIn.getX() + 1000, playerIn.getY() + 1000, playerIn.getZ() + (2000 / 2d)))
                                .stream().sorted(new Object() {
                                    Comparator<Entity> compareDistOf(double ax, double ay, double az) {
                                        return Comparator.comparing((Function<Entity, Double>) (entcnd -> entcnd.distanceToSqr(ax, ay, az)));
                                    }
                                }.compareDistOf(playerIn.getX(), playerIn.getY(), playerIn.getZ())).collect(Collectors.toList());
                        for (Entity entityiterator : entfound) {
                            if ((entityiterator instanceof PlayerEntity)) {
                                ((PlayerEntity) entityiterator).displayClientMessage(new StringTextComponent(
                                        sender + (new TranslationTextComponent("signal.msg.1").getString())
                                                + (new TranslationTextComponent("signal.msg.type." + stype).getString())
                                                + (new TranslationTextComponent("signal.msg.locis").getString())
                                                + "("+actualX+","+actualY+","+actualZ+")."
                                ),false);
                                ((PlayerEntity) entityiterator).playSound(soundEvent,1.0F,1.0F);
                            }
                        }
                    }
                    itemstack.getOrCreateTag().putInt("currentpower",itemstack.getOrCreateTag().getInt("currentpower") - 20000);
                }else{playerIn.sendMessage(new TranslationTextComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            case 1:
                if(itemstack.getOrCreateTag().getInt("currentpower")>=60000){
                    List<? extends PlayerEntity> _players = new ArrayList<>(worldIn.players());
                    for (Entity entityiterator : _players) {
                        if ((entityiterator instanceof PlayerEntity)) {
                            ((PlayerEntity) entityiterator).displayClientMessage(new StringTextComponent(
                                    sender + (new TranslationTextComponent("signal.msg.2").getString())
                                            + (new TranslationTextComponent("signal.msg.type." + stype).getString())
                                            + (new TranslationTextComponent("signal.msg.locis").getString())
                                            + "("+actualX+","+actualY+","+actualZ+")."
                            ),false);
                            ((PlayerEntity) entityiterator).playSound(soundEvent,1.0F,1.0F);
                        }}itemstack.getOrCreateTag().putInt("currentpower",itemstack.getOrCreateTag().getInt("currentpower") - 60000);
                }else{playerIn.sendMessage(new TranslationTextComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            case 2:
                if(itemstack.getOrCreateTag().getInt("currentpower")>=90000){
                    //if (!worldIn.isClientSide()) {
                        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
                        //if (server != null){
                            server.getPlayerList().broadcastMessage(new StringTextComponent(
                                    sender + (new TranslationTextComponent("signal.msg.3").getString())
                                            + (new TranslationTextComponent("signal.msg.type." + stype).getString())
                                            + (new TranslationTextComponent("signal.msg.locis").getString())
                                            + "("+actualX+","+actualY+","+actualZ+")."
                            ), ChatType.SYSTEM,playerIn.getUUID());
                    List<? extends PlayerEntity> _players = new ArrayList<>(worldIn.players());
                    for (Entity entityiterator : _players) {
                        if ((entityiterator instanceof PlayerEntity)) {
                            ((PlayerEntity) entityiterator).playSound(soundEvent,1.0F,1.0F);
                        }}
                    itemstack.getOrCreateTag().putInt("currentpower",itemstack.getOrCreateTag().getInt("currentpower") - 90000);}
                else{playerIn.sendMessage(new TranslationTextComponent("signal.msg.insufficientpower"), playerIn.getUUID());}
                break;
            default:
        }
    }

}