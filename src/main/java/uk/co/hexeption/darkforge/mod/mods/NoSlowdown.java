package uk.co.hexeption.darkforge.mod.mods;

import uk.co.hexeption.darkforge.event.EventTarget;
import uk.co.hexeption.darkforge.event.events.movement.PostMotionUpdateEvent;
import uk.co.hexeption.darkforge.mod.Mod;

/**
 * Created by Hexeption on 13/03/2017.
 */
@Mod.ModInfo(name = "No Slowdown", description = "Stops items from slowing you down.", category = Mod.Category.MOVEMENT)
public class NoSlowdown extends Mod {

    @EventTarget
    public void PostMotionUpdate(PostMotionUpdateEvent event) {

    }


}
