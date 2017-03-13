/*******************************************************************************
 *     DarkForge a Forge Hacked Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package uk.co.hexeption.mcwrapper.mixin.entity;

import net.minecraft.entity.MoverType;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import uk.co.hexeption.mcwrapper.base.entity.Entity;

/**
 * Created by Keir on 13/03/2017.
 */
@Mixin(net.minecraft.entity.Entity.class)
public abstract class MixinEntity implements Entity {

    @Shadow
    public World world;

    @Shadow
    public double prevPosX;

    @Shadow
    public double prevPosY;

    @Shadow
    public double prevPosZ;

    @Shadow
    public double posX;

    @Shadow
    public double posY;

    @Shadow
    public double posZ;

    @Shadow
    public double motionX;

    @Shadow
    public double motionY;

    @Shadow
    public double motionZ;

    @Shadow
    public float rotationYaw;

    @Shadow
    public float rotationPitch;

    @Shadow
    public boolean onGround;

    @Shadow
    public boolean isDead;

    @Shadow
    public float fallDistance;

    @Shadow
    public float stepHeight;

    @Shadow
    public boolean noClip;

    @Shadow
    public void move(MoverType type, double x, double y, double z) {

    }

    @Override
    public World getWorld() {

        return world;
    }
}
