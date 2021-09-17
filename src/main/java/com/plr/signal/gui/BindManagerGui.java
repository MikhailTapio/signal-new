package com.plr.signal.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.plr.signal.Utils;
import com.plr.signal.action.ConnectDevice;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;


public class BindManagerGui extends Screen {
    Button bind;
    Button unbind;
    Button connect;
    Button back;
    ResourceLocation COMMUNICATOR_MAINGUI_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/commgui1.png");
    ResourceLocation BATTERY_BASE = new ResourceLocation(Utils.MOD_ID,"textures/gui/batterybase.png" );
    ResourceLocation BATTERY_CONTENT = new ResourceLocation(Utils.MOD_ID, "textures/gui/batterycontent.png");
    TranslationTextComponent rdmanager = new TranslationTextComponent("signal.gui.remotedevicemanager");

    protected BindManagerGui(ITextComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        this.bind = new Button(guiLeft + 5, guiTop + 78, 35, 20
                ,new TranslationTextComponent("signal.gui.bind"), (button) -> {
            if(this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("binding") == 0 &&
                    this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bound") == 0){
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putInt("binding", 1);
                this.minecraft.player.sendMessage(new TranslationTextComponent("signal.msg.bindtutor"),this.minecraft.player.getUUID());
            }
            });
        this.unbind = new Button(guiLeft + 45, guiTop + 78, 40, 20
                ,new TranslationTextComponent("signal.gui.unbind"), (button) ->{
            if(this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1){
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putInt("bound", 0);
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putInt("bindx", 0);
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putInt("bindy", 0);
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putInt("bindz", 0);
                this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().putString("binddim", "");
                this.minecraft.player.playSound(new SoundEvent(new ResourceLocation(Utils.MOD_ID, "unbound")),1.0F,1.0F);
            }
        });
        this.connect = new Button(guiLeft + 89, guiTop + 78, 48, 20
                ,new TranslationTextComponent("signal.gui.connect"), (button) ->{
            if(this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1) {
                if (this.minecraft.player.level.dimension().toString().equals(this.minecraft.player.getItemInHand(Hand.MAIN_HAND)
                        .getOrCreateTag().getString("binddim"))) {
                    ConnectDevice.ConnectDevice(this.minecraft.player.level, this.minecraft.player
                            , this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bindx")
                            , this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bindy")
                            , this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bindz"));
                }else{
                    this.minecraft.player.sendMessage(new TranslationTextComponent("signal.msg.difdim"),this.minecraft.player.getUUID());
                }
            }
        });
        this.back = new Button(guiLeft + 119, guiTop + 151, 30, 20,
                new TranslationTextComponent("signal.gui.back"), (button) -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        });
        this.addButton(bind);
        this.addButton(unbind);
        this.addButton(connect);
        this.addButton(back);
        super.init();
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bind(COMMUNICATOR_MAINGUI_TEXTURE);
        int textureWidth = 150;
        int textureHeight = 150;
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        blit(matrixStack, this.width / 2 - 75, this.height / 2 - 75, 0, 0, 150, 150, textureWidth, textureHeight);
        //Battery
        this.minecraft.getTextureManager().bind(BATTERY_BASE);
        blit(matrixStack, guiLeft + 119, guiTop + 1, 0, 0, 16, 16, 16, 16);
        this.minecraft.getTextureManager().bind(BATTERY_CONTENT);
        blit(matrixStack, guiLeft + 122, guiTop + 1, 0, 0, Math.toIntExact(11 * this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("currentpower") / 100000), 16, 16, 16);
        //BindManagerTitle
        this.font.draw(matrixStack, rdmanager , guiLeft + 6, guiTop + 4, -1);
        //BindStatus
        this.font.draw(matrixStack, new TranslationTextComponent("signal.gui.bindstatus."+this.minecraft.player.getItemInHand(Hand.MAIN_HAND)
                .getOrCreateTag().getInt("bound")+"") , guiLeft + 6, guiTop + 24, -12829636);
        //Locations
        if (this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1){
            this.font.draw(matrixStack, "X =", guiLeft + 6, guiTop + 37, -12829636);
            this.font.draw(matrixStack, "Y =", guiLeft + 6, guiTop + 50, -12829636);
            this.font.draw(matrixStack, "Z =", guiLeft + 6, guiTop + 63, -12829636);
            this.font.draw(matrixStack, this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                    .getInt("bindx")+"", guiLeft + 24, guiTop + 37, -12829636);
            this.font.draw(matrixStack, this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                    .getInt("bindy")+"", guiLeft + 24, guiTop + 50, -12829636);
            this.font.draw(matrixStack, this.minecraft.player.getItemInHand(Hand.MAIN_HAND).getOrCreateTag()
                    .getInt("bindz")+"", guiLeft + 24, guiTop + 63, -12829636);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
