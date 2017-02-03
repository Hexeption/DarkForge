package uk.co.hexeption.darkforge.module.modules;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.input.Keyboard;
import uk.co.hexeption.darkforge.api.logger.LogHelper;
import uk.co.hexeption.darkforge.module.Module;
import uk.co.hexeption.darkforge.utils.RenderUtils;

/**
 * Created by Hexeption on 03/02/2017.
 */
@Module.ModInfo(name = "Tracer", description = "Draws a line to a player/mob/friends", category = Module.Category.RENDER, bind = Keyboard.KEY_P)
public class Tracers extends Module {

    //TODO: Values
    public static boolean player = true, mob ;

    public static double ticks;

    public static double x, y, z;

    @Override
    public void onWorldRender() {

        for (Object entityList : getWorld().loadedEntityList) {
            if (!(entityList instanceof EntityLivingBase)) {
                continue;
            }

            EntityLivingBase entity = (EntityLivingBase) entityList;

            if (player) {
                if (entity instanceof EntityPlayer) {
                    if (entity != getPlayer() && !entity.isInvisible()) {
                        player(entity);

                    }
                }
            }
            mob = true;

            if(mob){
                if( entity instanceof EntityMob && entity instanceof EntityAnimal){
                    player(entity);
                }
            }


        }
    }

    private void player(EntityLivingBase entity) {

        render(1, 1, 1, 1, entity);
    }

    private void render(float red, float green, float blue, float alpha, EntityLivingBase entityLivingBase) {

        double renderPosX = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosX");
        double renderPosY = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosY");
        double renderPosZ = ReflectionHelper.getPrivateValue(RenderManager.class, getMinecraft().getRenderManager(), "renderPosZ");
        double xPos = (entityLivingBase.lastTickPosX + (entityLivingBase.posX - entityLivingBase.lastTickPosX) * ticks) - renderPosX;
        double yPos = (entityLivingBase.lastTickPosY + (entityLivingBase.posY - entityLivingBase.lastTickPosY) * ticks) - renderPosY;
        double zPos = (entityLivingBase.lastTickPosZ + (entityLivingBase.posZ - entityLivingBase.lastTickPosZ) * ticks) - renderPosZ;
//        LogHelper.info("X:" + x + " Y:" + y + " Z:" + z);
        RenderUtils.drawTracer(xPos, yPos, zPos, 2, red, green, blue, alpha);



    }


}
