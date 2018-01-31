package kanethornwyrd.mods.norsecraft.modules.metals.blastFurnace;

import kanethornwyrd.mods.norsecraft.modules.metals.BlockNames;

import net.minecraft.block.BlockFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

import static kanethornwyrd.mods.norsecraft.Misc.MOD_ID;


public class GUIBlastFurnace extends GuiContainer {

private static final ResourceLocation BG_TEXTURE = new ResourceLocation(MOD_ID, "textures/gui/pedestal.png");
private InventoryPlayer playerInv;
private TileEntityBlastFurnace tile;

public GUIBlastFurnace( TileEntityBlastFurnace tile, InventoryPlayer playerInv ) {
  super(new ContainerBlastFurnace(tile, playerInv));
  this.playerInv = playerInv;
  this.tile = tile;
}

@Override
protected void drawGuiContainerForegroundLayer( int mouseX, int mouseY ) {
  String name = I18n.format(BlockNames.BLASTFURNACE);
  fontRenderer.drawString(name, xSize / 2 - fontRenderer.getStringWidth(name) / 2, 6, 0x404040);
  fontRenderer.drawString(playerInv.getDisplayName().getUnformattedText(), 8, ySize - 94, 0x404040);
}

@Override
protected void drawGuiContainerBackgroundLayer( float partialTicks, int mouseX, int mouseY ) {
  int furnaceBurnTime = this.tile.getField(0);
  int currentItemBurnTime = this.tile.getField(1);
  int cookTime = this.tile.getField(2);
  int totalCookTime = this.tile.getField(3);
  
  
  GlStateManager.color(1, 1, 1, 1);
  int x = (width - xSize) / 2;
  int y = (height - ySize) / 2;
  this.drawDefaultBackground();
  mc.getTextureManager().bindTexture(BG_TEXTURE);
  drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
  
  int textureWidth = (int) (23f / totalCookTime * cookTime);
  this.drawTexturedModalRect(x + 80, y + 34, 177, 14, textureWidth, 16);
  
  if (this.tile.isBurning()) {
    int burningTime = this.tile.getField(0);
    int textureHeight = (int) (14f / currentItemBurnTime * furnaceBurnTime);
    this.drawTexturedModalRect(x + 56, y + 36 + 14 - textureHeight,
                               176, 14 - textureHeight, 16, textureHeight);
  }
}
}
