package com.conkcreets.reelresources.screens;

import com.conkcreets.reelresources.ReelResources;
import com.conkcreets.reelresources.menus.AquariumMenu;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class AquariumScreen extends AbstractContainerScreen<AquariumMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(ReelResources.MODID, "textures/gui/aquarium_gui.png");

    public AquariumScreen(AquariumMenu menu, Inventory playerInventory, Component title) 
    {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() 
    {
        super.init();
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTicks, int mouseX, int mouseY) 
    {
        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, this.leftPos, this.topPos, 0, 0, imageWidth, imageHeight, 256, 256);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick)
    {
        super.render(graphics, mouseX, mouseY, partialTick);
        this.renderTooltip(graphics, mouseX, mouseY);
    }
    
}
