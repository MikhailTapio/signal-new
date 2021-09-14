package com.plr.signal.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.plr.signal.Utils;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

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
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        this.opstoggle = new Button(guiLeft + 7, guiTop + 49, 54, 20,
                new TranslationTextComponent("signal.gui.toggle." + !this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getBoolean("opson") + "" ), (button) -> {
            if(this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getBoolean("opson")){
            this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putBoolean("opson",false );
            }else if(!this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getBoolean("opson")){
            this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putBoolean("opson",true );
            }
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUI::new);
        });
        this.senda = new Button(guiLeft + 9, guiTop + 122, 41, 20,
                new TranslationTextComponent("signal.gui.sendsignal"), (button) -> {

        });
        this.remotecontrol = new Button(guiLeft + 54, guiTop + 122, 50, 20,
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
        this.font.draw(matrixStack, "" + (this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                .getLong("locx")), this.width / 2 - 51, this.height / 2 + 9, -12829636);
        this.font.draw(matrixStack, "" + (this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                .getLong("locy")), this.width / 2 - 51, this.height / 2 + 22, -12829636);
        this.font.draw(matrixStack, "" + (this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                .getLong("locz")), this.width / 2 - 51, this.height / 2 + 35, -12829636);
        this.font.draw(matrixStack, "X =", this.width / 2 - 69, this.height / 2 + 9, -12829636);
        this.font.draw(matrixStack, "Y =", this.width / 2 - 69, this.height / 2 + 22, -12829636);
        this.font.draw(matrixStack, "Z =", this.width / 2 - 69, this.height / 2 + 35, -12829636);

        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}