package com.plr.signal.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.plr.signal.common.Utils;
import com.plr.signal.common.action.SendSignal;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class CommunicatorSignalGui extends Screen {
    Button OneKm;
    Button ThisDim;
    Button OverServer;
    Button Distress;
    Button NeedResource;
    Button NeedHr;
    Button FindThreat;
    Button FindResource;
    Button Send;
    Button Anonymous;
    Button Back;
    ResourceLocation COMMUNICATOR_MAINGUI_TEXTURE = new ResourceLocation(Utils.MOD_ID, "textures/gui/commgui1.png");
    ResourceLocation BATTERY_BASE = new ResourceLocation(Utils.MOD_ID, "textures/gui/batterybase.png");
    ResourceLocation BATTERY_CONTENT = new ResourceLocation(Utils.MOD_ID, "textures/gui/batterycontent.png");
    ResourceLocation RANGE_INDICATOR = new ResourceLocation(Utils.MOD_ID,"textures/gui/indicator/indicator_green.png");
    ResourceLocation IND_DISTRESS = new ResourceLocation(Utils.MOD_ID,"textures/gui/indicator/indicator_red.png");
    ResourceLocation IND_RESOURCE = new ResourceLocation(Utils.MOD_ID,"textures/gui/indicator/indicator_cyan.png");
    ResourceLocation IND_MANPOWER = new ResourceLocation(Utils.MOD_ID,"textures/gui/indicator/indicator_yellow.png");
    ResourceLocation IND_THREAT = new ResourceLocation(Utils.MOD_ID,"textures/gui/indicator/indicator_orange.png");
    TranslatableComponent range = new TranslatableComponent("signal.gui.signalrange");
    TranslatableComponent type = new TranslatableComponent("signal.gui.signaltype");
    TranslatableComponent need = new TranslatableComponent("signal.gui.need");
    TranslatableComponent found = new TranslatableComponent("signal.gui.found");
    TranslatableComponent isanonymous = new TranslatableComponent("signal.gui.isanonymous");


    protected CommunicatorSignalGui(TranslatableComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        this.OneKm = new Button(guiLeft + 6, guiTop + 20, 24, 20,
                new TranslatableComponent("signal.gui.range.onekm"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("channel", 0);
        });
        this.ThisDim = new Button(guiLeft + 34, guiTop + 20, 36, 20,
                new TranslatableComponent("signal.gui.range.thisdim"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("channel", 1);
        });
        this.OverServer = new Button(guiLeft + 73, guiTop + 20, 40, 20,
                new TranslatableComponent("signal.gui.range.overserver"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("channel", 2);
        });
        this.Distress = new Button(guiLeft + 6, guiTop + 59, 46, 20,
                new TranslatableComponent("signal.gui.type.distress"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("type", 0);
        });
        this.NeedResource = new Button(guiLeft + 6, guiTop + 93, 54, 20,
                new TranslatableComponent("signal.gui.type.resource"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("type", 1);
        });
        this.NeedHr = new Button(guiLeft + 64, guiTop + 93, 52, 20,
                new TranslatableComponent("signal.gui.type.manpower"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("type", 2);
        });
        this.FindThreat = new Button(guiLeft + 6, guiTop + 125, 40, 20,
                new TranslatableComponent("signal.gui.type.threat"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("type", 3);
        });
        this.FindResource = new Button(guiLeft + 50, guiTop + 125, 54, 20,
                new TranslatableComponent("signal.gui.type.resource"), (button) -> {
            this.minecraft.player.getPersistentData().putInt("type", 4);
        });
        this.Send = new Button(guiLeft + 108, guiTop + 125, 30, 20,
                new TranslatableComponent("signal.gui.send"), (button) -> {
            ItemStack itemstack= this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND);
            Level world = this.minecraft.level;
            Entity entity = this.minecraft.player;
            SendSignal.sendSignal(itemstack,world,entity);
        });
        this.Back = new Button(guiLeft + 119, guiTop + 151, 30, 20,
                new TranslatableComponent("signal.gui.back"), (button) -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        });
        this.Anonymous = new Button(guiLeft + 118, guiTop + 59, 30, 20,
                new TranslatableComponent("signal.gui.isanonymous." + this.minecraft.player
                        .getPersistentData().getInt("ano") + ""), (button) -> {
            this.minecraft.player.getPersistentData().putInt("ano",
                    1 - this.minecraft.player.getPersistentData().getInt("ano"));
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIb::new);
        });
        this.addRenderableWidget(OneKm);
        this.addRenderableWidget(ThisDim);
        this.addRenderableWidget(OverServer);
        this.addRenderableWidget(Distress);
        this.addRenderableWidget(NeedResource);
        this.addRenderableWidget(NeedHr);
        this.addRenderableWidget(FindThreat);
        this.addRenderableWidget(FindResource);
        this.addRenderableWidget(Send);
        this.addRenderableWidget(Back);
        this.addRenderableWidget(Anonymous);
        super.init();
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        int textureWidth = 150;
        int textureHeight = 150;
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,COMMUNICATOR_MAINGUI_TEXTURE);
        blit(matrixStack, this.width / 2 - 75, this.height / 2 - 75, 0, 0, 150, 150, textureWidth, textureHeight);
        //Battery
        RenderSystem.setShaderTexture(0,BATTERY_BASE);
        blit(matrixStack, guiLeft + 119, guiTop + 1, 0, 0, 16, 16, 16, 16);
        RenderSystem.setShaderTexture(0,BATTERY_CONTENT);
        blit(matrixStack, guiLeft + 122, guiTop + 1, 0, 0, Math.toIntExact(11 * this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("currentpower") / 100000), 16, 16, 16);
        //RangeIndicator
        switch (this.minecraft.player.getPersistentData().getInt("channel")) {
            case 0:
                RenderSystem.setShaderTexture(0,RANGE_INDICATOR);
                blit(matrixStack, guiLeft + 4, guiTop + 20, 0, 0, 2, 20, 2, 20);
                break;
            case 1:
                RenderSystem.setShaderTexture(0,RANGE_INDICATOR);
                blit(matrixStack, guiLeft + 32, guiTop + 20, 0, 0, 2, 20, 2, 20);
                break;
            case 2:
                RenderSystem.setShaderTexture(0,RANGE_INDICATOR);
                blit(matrixStack, guiLeft + 71, guiTop + 20, 0, 0, 2, 20, 2, 20);
                break;
            default:
        }
        //TypeIndicator
        switch (this.minecraft.player.getPersistentData().getInt("type")){
            case 0:
                RenderSystem.setShaderTexture(0,IND_DISTRESS);
                blit(matrixStack, guiLeft + 4, guiTop + 59, 0, 0, 2, 20, 2, 20);
                break;
            case 1:
                RenderSystem.setShaderTexture(0,IND_RESOURCE);
                blit(matrixStack, guiLeft + 4, guiTop + 93, 0, 0, 2, 20, 2, 20);
                break;
            case 2:
                RenderSystem.setShaderTexture(0,IND_MANPOWER);
                blit(matrixStack, guiLeft + 62, guiTop + 93, 0, 0, 2, 20, 2, 20);
                break;
            case 3:
                RenderSystem.setShaderTexture(0,IND_THREAT);
                blit(matrixStack, guiLeft + 4, guiTop + 125, 0, 0, 2, 20, 2, 20);
                break;
            case 4:
                RenderSystem.setShaderTexture(0,IND_RESOURCE);
                blit(matrixStack, guiLeft + 48, guiTop + 125, 0, 0, 2, 20, 2, 20);
                break;
            default:
        }
        //SignalTitle
        this.font.draw(matrixStack, range , this.width / 2 - 69, this.height / 2 - 75 + 4, -1);
        this.font.draw(matrixStack, type ,guiLeft + 5,guiTop + 45, -12829636);
        this.font.draw(matrixStack, need ,guiLeft + 5,guiTop + 82, -12829636);
        this.font.draw(matrixStack, found ,guiLeft + 5,guiTop + 115, -12829636);
        this.font.draw(matrixStack, isanonymous ,guiLeft + 54,guiTop + 64, -12829636);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }
}
