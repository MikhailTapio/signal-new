package com.plr.signal.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGUIb {
    public OpenGUIb() { Minecraft.getInstance().setScreen(new CommunicatorSignalGui(new TranslationTextComponent(""))); }
}
