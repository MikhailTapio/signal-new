package com.plr.signal.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class OpenGUIa {
    public OpenGUIa() {
        Minecraft.getInstance().setScreen(new CommunicatorMainGui(new TranslatableComponent("")));
    }
}
