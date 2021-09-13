package com.plr.signal.gui;

import com.plr.signal.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TranslationTextComponent;

public class OpenGUI {
    public OpenGUI() {
        Minecraft.getInstance().setScreen(new CommunicatorMainGui(new TranslationTextComponent(Utils.MOD_ID + ".emptyword")));
    }
}
