package com.plr.signal.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.plr.signal.common.Utils;
import com.plr.signal.common.block.ChargingStandContainer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.player.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ChargingStandScreen extends AbstractContainerScreen<ChargingStandContainer> {
    private final ResourceLocation BACKGROUND = new ResourceLocation(Utils.MOD_ID, "textures/gui/chargingscreen.png");
    private final ResourceLocation BATTERY = new ResourceLocation(Utils.MOD_ID, "textures/gui/csbattery.png");

    public ChargingStandScreen(ChargingStandContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    //render labels
    @Override
    protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
        int relX = (this.width - 180) / 2;
        int relY = (this.height - 152) / 2;
        List<FormattedCharSequence> tooltip1 = new ArrayList<FormattedCharSequence>();
        //Energy tooltip display
        if(mouseX > relX + 31 && mouseX < relX + 164 && mouseY > relY + 53 && mouseY < relY + 61){
            tooltip1.add(new TextComponent(new TranslatableComponent("signal.tooltip.energy").getString()+
                    menu.getEnergy()+
                    " FE").getVisualOrderText());
            renderTooltip(matrixStack,tooltip1,mouseX - relX,mouseY - relY);}
    }

    protected void renderBg(PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        //Background
        RenderSystem.setShaderTexture(0, BACKGROUND);
        int relX = (this.width - 180) / 2;
        int relY = (this.height - 152) / 2;
        blit(matrixStack, relX, relY, 0, 0, 180, 152, 180, 152);
        //Battery
        RenderSystem.setShaderTexture(0,BATTERY);
        double ce = menu.getEnergy();
        double me = menu.getMaxEnergy();
        int length = Math.toIntExact(Math.round((ce / me) * 132));
        blit(matrixStack, relX + 32, relY + 54, 0, 0, length,
                7,132,7);
    }
}
