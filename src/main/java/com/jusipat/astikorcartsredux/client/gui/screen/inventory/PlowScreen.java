package com.jusipat.astikorcartsredux.client.gui.screen.inventory;

import com.jusipat.astikorcartsredux.AstikorCartsRedux;

import com.jusipat.astikorcartsredux.inventory.container.CartContainer;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public final class PlowScreen extends AbstractContainerScreen<CartContainer> {
    private static final ResourceLocation PLOW_GUI_BG = new ResourceLocation(AstikorCartsRedux.ID, "textures/gui/plow.png");

    public PlowScreen(final CartContainer screenContainer, final Inventory inv, final Component titleIn) {
        super(screenContainer, inv, titleIn);
    }


    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        renderBackground(guiGraphics);
        guiGraphics.blit(PLOW_GUI_BG, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight);
        this.renderTooltip(guiGraphics, mouseX, mouseY);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderBg(GuiGraphics p_283065_, float p_97788_, int p_97789_, int p_97790_) {

    }
}