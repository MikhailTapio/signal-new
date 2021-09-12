package com.plr.signal.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.plr.signal.Utils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class CommunicatorMainGui extends Screen {
    Button opstoggle;
    Button senda;
    Button remotecontrol;
    ResourceLocation COMMUNICATOR_MAINGUI_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/commgui1.png");
    TranslationTextComponent comm = new TranslationTextComponent("signal.gui.comm");
    TranslationTextComponent owner = new TranslationTextComponent("signal.gui.owner");
    TranslationTextComponent ops = new TranslationTextComponent("signal.gui.ops");

    protected CommunicatorMainGui(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        this.opstoggle = new Button(this.width / 2 - 40, 96, 80, 20,
                new TranslationTextComponent("signal.gui.toggle"), (button) -> {

        });
        this.senda = new Button(this.width / 2 - 40, 96, 80, 20,
                new TranslationTextComponent("signal.gui.send"), (button) -> {

        });
        this.remotecontrol = new Button(this.width / 2 - 40, 96, 80, 20,
                new TranslationTextComponent("signal.gui.rcontrol"), (button) -> {

        });
        this.addButton(opstoggle);
        this.addButton(senda);
        this.addButton(remotecontrol);
        super.init();
    }
    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(COMMUNICATOR_MAINGUI_TEXTURE);
        int textureWidth = 150;
        int textureHeight = 150;
        blit(matrixStack, this.width / 2 - 75, this.height / 2 - 75, 0, 0, 150, 150, textureWidth, textureHeight);

    }
}