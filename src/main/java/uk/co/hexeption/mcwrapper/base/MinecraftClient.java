package uk.co.hexeption.mcwrapper.base;

import uk.co.hexeption.mcwrapper.base.multiplayer.Controller;

/**
 * Created by Hexeption on 13/03/2017.
 */
public interface MinecraftClient {

    Controller getController();


    float getTimerSpeed();

    void setTimerSpeed(float timerSpeed);

    int getFPS();

}
