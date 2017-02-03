package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static org.lwjgl.opengl.GL11.*;

import uk.co.hexeption.darkforge.api.annotation.TestClass;
import uk.co.hexeption.darkforge.module.Module;

import java.util.LinkedList;

/**
 * Created by Hexeption on 15/01/2017.
 */
@SideOnly(Side.CLIENT)
@TestClass
@Module.ModInfo(name = "Bread Crumbs", description = "Leaves a trail behind you", category = Module.Category.RENDER, bind = 0)
public class BreadCrumbs extends Module {

    private final LinkedList<double[]> positions = new LinkedList<double[]>();

    @Override
    public void onWorldTick() {

        synchronized (positions) {
            positions.add(new double[]{getPlayer().posX, getPlayer().posY, getPlayer().posZ});
        }
    }

    @Override
    public void onWorldRender() {

        synchronized (positions) {
            glPushMatrix();

            getEntityRenderer().disableLightmap();
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
            glLineWidth(1.5f);
            glDisable(GL_LIGHTING);
            glDisable(GL_TEXTURE_2D);
            glEnable(GL_LINE_SMOOTH);
            glDisable(GL_DEPTH_TEST);
            glDepthMask(false);
            glBegin(GL_LINE_STRIP);
            glColor4d(0, 0.7D, 0.7D, 1);
            double renderPosX = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosX");
            double renderPosY = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosY");
            double renderPosZ = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosZ");

            for (final double[] pos : positions) {
                glVertex3d(pos[0] - renderPosX , pos[1] - renderPosY, pos[2] - renderPosZ);
            }

            glColor4d(1, 1, 1, 1);
            glEnd();
            glDisable(GL_LINE_SMOOTH);
            glEnable(GL_TEXTURE_2D);
            glEnable(GL_LIGHTING);
            glEnable(GL_DEPTH_TEST);
            glDepthMask(true);
//            glDisable(GL_BLEND);
            getEntityRenderer().enableLightmap();
            glPopMatrix();
        }
    }
}
