package com.plr.signal.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class OpenGUIb {
    public OpenGUIb() { Minecraft.getInstance().setScreen(new CommunicatorSignalGui(new TranslatableComponent(""))); }
}
