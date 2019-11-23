package mchorse.blockbuster.client.render.tileentity;

import mchorse.blockbuster.Blockbuster;
import mchorse.blockbuster.client.gui.dashboard.panels.model_editor.GuiBBModelRenderer;
import mchorse.blockbuster.common.block.BlockDirector;
import mchorse.blockbuster.common.tileentity.TileEntityDirector;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

public class TileEntityDirectorRenderer extends TileEntitySpecialRenderer<TileEntityDirector>
{
    @Override
    public void renderTileEntityAt(TileEntityDirector te, double x, double y, double z, float partialTicks, int destroyStage)
    {
        Minecraft mc = Minecraft.getMinecraft();

        /* Debug render (so people could find the block, lmao) */
        if (mc.gameSettings.showDebugInfo && !mc.gameSettings.hideGUI)
        {
            IBlockState state = mc.theWorld.getBlockState(te.getPos());
            boolean playing = state.getBlock() == Blockbuster.directorBlock ? state.getValue(BlockDirector.PLAYING) : false;

            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GlStateManager.enableBlend();

            if (playing)
            {
                GuiBBModelRenderer.drawCube(x + 0.25F, y + 0.25F, z + 0.25F, x + 0.75F, y + 0.75F, z + 0.75F, 0, 1, 0, 0.5F);
            }
            else
            {
                GuiBBModelRenderer.drawCube(x + 0.25F, y + 0.25F, z + 0.25F, x + 0.75F, y + 0.75F, z + 0.75F, 1, 0, 0, 0.5F);
            }

            GlStateManager.disableBlend();
            GlStateManager.enableLighting();
            GlStateManager.enableDepth();
        }
    }
}