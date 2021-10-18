package com.plr.signal.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class OpenGUIb {
    public OpenGUIb() { Minecraft.getInstance().setScreen(new CommunicatorSignalGui(new TranslatableComponent(""))); }
}
