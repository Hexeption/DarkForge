package uk.co.hexeption.darkforge.event.events.render;

import uk.co.hexeption.darkforge.event.Event;

/**
 * Created by Hexeption on 13/03/2017.
 */
public class EventRender3D extends Event {

    private float partialTicks;

    public EventRender3D(float partialTicks) {

        this.partialTicks = partialTicks;
    }

    public float getPartialTicks() {

        return partialTicks;
    }

    public void setPartialTicks(float partialTicks) {

        this.partialTicks = partialTicks;
    }
}
