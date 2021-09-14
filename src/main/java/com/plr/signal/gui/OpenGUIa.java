package com.plr.signal.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGUIa {
    public OpenGUIa() {
        Minecraft.getInstance().setScreen(new CommunicatorMainGui(new TranslationTextComponent("")));
    }
}
