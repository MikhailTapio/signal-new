package com.plr.signal.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.TranslatableComponent;

public class OpenGUIa {
    public OpenGUIa() {
        Minecraft.getInstance().setScreen(new CommunicatorMainGui(new TranslatableComponent("")));
    }
}
