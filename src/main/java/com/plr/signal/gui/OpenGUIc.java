package com.plr.signal.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGUIc {
    public OpenGUIc() { Minecraft.getInstance().setScreen(new BindManagerGui(new TranslationTextComponent(""))); }
}
