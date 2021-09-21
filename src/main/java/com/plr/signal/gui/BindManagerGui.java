package com.plr.signal.gui;

/*import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.plr.signal.Utils;
import com.plr.signal.action.ConnectDevice;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
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
    TranslatableComponent rdmanager = new TranslatableComponent("signal.gui.remotedevicemanager");

    protected BindManagerGui(TranslatableComponent titleIn) {
        super(titleIn);
    }

    @Override
    protected void init() {
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        this.bind = new Button(guiLeft + 5, guiTop + 78, 35, 20
                ,new TranslatableComponent("signal.gui.bind"), (button) -> {
            if(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("binding") == 0 &&
                    this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("bound") == 0){
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putInt("binding", 1);
                this.minecraft.player.sendMessage(new TranslatableComponent("signal.msg.bindtutor"),this.minecraft.player.getUUID());
            }
            });
        this.unbind = new Button(guiLeft + 45, guiTop + 78, 40, 20
                ,new TranslatableComponent("signal.gui.unbind"), (button) ->{
            if(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1){
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putInt("bound", 0);
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putDouble("bindx", 0);
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putDouble("bindy", 0);
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putDouble("bindz", 0);
                this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().putString("binddim", "");
                this.minecraft.player.playSound(new SoundEvent(new ResourceLocation(Utils.MOD_ID, "unbound")),1.0F,1.0F);
            }
        });
        this.connect = new Button(guiLeft + 89, guiTop + 78, 48, 20
                ,new TranslatableComponent("signal.gui.connect"), (button) ->{
            if(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1) {
                if (this.minecraft.player.level.dimension().toString().equals(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND)
                        .getOrCreateTag().getString("binddim"))) {
                    ConnectDevice.ConnectDevice(this.minecraft.player.level, this.minecraft.player
                            , this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getDouble("bindx")
                            , this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getDouble("bindy")
                            , this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getDouble("bindz"));
                }else{
                    this.minecraft.player.sendMessage(new TranslatableComponent("signal.msg.difdim"),this.minecraft.player.getUUID());
                }
            }
        });
        this.back = new Button(guiLeft + 119, guiTop + 151, 30, 20,
                new TranslatableComponent("signal.gui.back"), (button) -> {
            DistExecutor.safeCallWhenOn(Dist.CLIENT, () -> OpenGUIa::new);
        });
        this.addRenderableWidget(bind);
        this.addRenderableWidget(unbind);
        this.addRenderableWidget(connect);
        this.addRenderableWidget(back);
        super.init();
    }




    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        this.minecraft.getTextureManager().bindForSetup(COMMUNICATOR_MAINGUI_TEXTURE);
        int textureWidth = 150;
        int textureHeight = 150;
        int guiLeft = this.width / 2 - 75;
        int guiTop = this.height / 2 - 75;
        blit(matrixStack, this.width / 2 - 75, this.height / 2 - 75, 0, 0, 150, 150, textureWidth, textureHeight);
        //Battery
        this.minecraft.getTextureManager().bindForSetup(BATTERY_BASE);
        blit(matrixStack, guiLeft + 119, guiTop + 1, 0, 0, 16, 16, 16, 16);
        this.minecraft.getTextureManager().bindForSetup(BATTERY_CONTENT);
        blit(matrixStack, guiLeft + 122, guiTop + 1, 0, 0, Math.toIntExact(11 * this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("currentpower") / 100000), 16, 16, 16);
        //BindManagerTitle
        this.font.draw(matrixStack, rdmanager , guiLeft + 6, guiTop + 4, -1);
        //BindStatus
        this.font.draw(matrixStack, new TranslatableComponent("signal.gui.bindstatus."+this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND)
                .getOrCreateTag().getInt("bound")+"") , guiLeft + 6, guiTop + 24, -12829636);
        //Locations
        if (this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag().getInt("bound") == 1){
            this.font.draw(matrixStack, "X =", guiLeft + 6, guiTop + 37, -12829636);
            this.font.draw(matrixStack, "Y =", guiLeft + 6, guiTop + 50, -12829636);
            this.font.draw(matrixStack, "Z =", guiLeft + 6, guiTop + 63, -12829636);
            this.font.draw(matrixStack, Math.round(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag()
                    .getDouble("bindx"))+"", guiLeft + 24, guiTop + 37, -12829636);
            this.font.draw(matrixStack, Math.round(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag()
                    .getDouble("bindy"))+"", guiLeft + 24, guiTop + 50, -12829636);
            this.font.draw(matrixStack, Math.round(this.minecraft.player.getItemInHand(InteractionHand.MAIN_HAND).getOrCreateTag()
                    .getDouble("bindz"))+"", guiLeft + 24, guiTop + 63, -12829636);
        }
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

}*/
