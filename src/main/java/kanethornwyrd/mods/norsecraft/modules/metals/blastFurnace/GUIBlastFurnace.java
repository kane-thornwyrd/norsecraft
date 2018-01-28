package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import kanethornwyrd.mods.norsecraft.modules.metals.BlockNames;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;


public class GUIBlastFurnace extends GuiContainer {

private static final ResourceLocation BG_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/pedestal.png");
private InventoryPlayer playerInv;

public GUIBlastFurnace( Container container, InventoryPlayer playerInv ) {
  super(container);
  this.playerInv = playerInv;
}

@Override
protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY ) {
  String name = I18n.format(BlockNames.BLASTFURNACE);
  fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
  fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
}

@Override
protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ) {
  GlStateManager.color(1, 1, 1, 1);
  mc.getTextureManager().bindTexture(BG_TEXTURE);
  int x = (width - xSize) / 2;
  int y = (height - ySize) / 2;
  drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
}

}
