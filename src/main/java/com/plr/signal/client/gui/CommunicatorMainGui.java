package com.plr.signal.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.plr.signal.common.Utils;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class CommunicatorMainGui extends Screen {
    public static final ResourceLocation COMMUNICATOR_MAINGUI_TEXTURE = new ResourceLocation(Utils.MOD_ID,"textures/gui/commgui1.png" );
    public static final ResourceLocation BATTERY_BASE = new ResourceLocation(Utils.MOD_ID,"textures/gui/batterybase.png" );
    public static final ResourceLocation BATTERY_CONTENT = new ResourceLocation(Utils.MOD_ID, "textures/gui/batterycontent.png");
    Button opstoggle;
    Button senda;
    //Button remotecontrol;



    TranslatableComponent comm = new TranslatableComponent("item.signal.communicator");
    TranslatableComponent owner = new TranslatableComponent("signal.gui.owner");
    TranslatableComponent ops = new TranslatableComponent("signal.gui.ops");


    protected CommunicatorMainGui(TranslatableComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        this.opstoggle = new Button(guiLeft + 7, guiTop + 49, 54, 20,
                new TranslatableComponent("signal.gui.toggle."+ !this.minecraft.player.getPersistentData().getBoolean("toggle") +""), (button) -> {
            this.minecraft.player.getPersistentData().putBoolean("toggle", !this.minecraft.player.getPersistentData().getBoolean("toggle"));
            SoundEvent soundevent = null;
            if(this.minecraft.player.getPersistentData().getBoolean("toggle")){
                soundevent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "opson"));
            }else{
                soundevent = new SoundEvent(new ResourceLocation(Utils.MOD_ID, "opsoff"));
            }
            this.minecraft.player.playSound(soundevent,1.0F,1.0F);
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        });
        this.senda = new Button(guiLeft + 9, guiTop + 122, 41, 20,
                new TranslatableComponent("signal.gui.sendsignal"), (button) -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIb::new);
        });
        /*this.remotecontrol = new Button(guiLeft + 54, guiTop + 122, 50, 20,
                new TranslationTextComponent("signal.gui.rcontrol"), (button) -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIc::new);
        });*/
        this.addRenderableWidget(opstoggle);
        this.addRenderableWidget(senda);
        //this.addButton(remotecontrol);
        super.init();
    }


    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,COMMUNICATOR_MAINGUI_TEXTURE);
        blit(matrixStack, this.width / 2 - 75, this.height / 2 - 75, 0, 0, 150, 150, 150, 150);
        //Battery
        RenderSystem.setShaderTexture(0,BATTERY_CONTENT);
        blit(matrixStack, guiLeft + 122, guiTop + 1, 0, 0, Math.toIntExact(11 * this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("currentpower") / 100000), 16, 16, 16);
        RenderSystem.setShaderTexture(0,BATTERY_BASE);
        blit(matrixStack, guiLeft + 119, guiTop + 1, 0, 0, 16, 16, 16, 16);
        //CommunicatorTitle
        this.font.draw(matrixStack, comm , this.width / 2 - 69, this.height / 2 - 75 + 4, -1);
        //Owner
        this.font.draw(matrixStack, owner.getString() + this.minecraft.player.getUseItem().getOrCreateTag().getString("ownername") , this.width / 2 - 69, this.height / 2 - 75 + 21,-12829636);
        //OPS
        this.font.draw(matrixStack, ops , this.width / 2 - 69, this.height / 2 - 40, -12829636);
        if(this.minecraft.player.getPersistentData().getBoolean("toggle")){
            this.font.draw(matrixStack,new TranslatableComponent("signal.gui.on"),
                    this.width / 2 - 6, this.height / 2 - 40, -12829636);
        }else{
            this.font.draw(matrixStack,new TranslatableComponent("signal.gui.off"),
                    this.width / 2 - 6, this.height / 2 - 40, -12829636);
        }
        //Location
        if (this.minecraft.player.getPersistentData().getBoolean("toggle"))
        {
            this.font.draw(matrixStack, "" + (this.minecraft.player.getPersistentData()
                    .getLong("locx")), this.width / 2 - 51, this.height / 2 + 9, -12829636);
            this.font.draw(matrixStack, "" + (this.minecraft.player.getPersistentData()
                    .getLong("locy")), this.width / 2 - 51, this.height / 2 + 22, -12829636);
            this.font.draw(matrixStack, "" + (this.minecraft.player.getPersistentData()
                    .getLong("locz")), this.width / 2 - 51, this.height / 2 + 35, -12829636);
            this.font.draw(matrixStack, "X =", this.width / 2 - 69, this.height / 2 + 9, -12829636);
            this.font.draw(matrixStack, "Y =", this.width / 2 - 69, this.height / 2 + 22, -12829636);
            this.font.draw(matrixStack, "Z =", this.width / 2 - 69, this.height / 2 + 35, -12829636);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}