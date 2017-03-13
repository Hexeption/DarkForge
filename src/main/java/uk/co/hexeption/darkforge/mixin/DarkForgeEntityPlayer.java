package uk.co.hexeption.darkforge.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.stats.StatisticsManager;
import net.minecraft.world.World;
import uk.co.hexeption.darkforge.DarkForge;

/**
 * Created by Hexeption on 13/03/2017.
 */
public class DarkForgeEntityPlayer extends EntityPlayerSP {

    public DarkForgeEntityPlayer(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatisticsManager statFile) {

        super(mcIn, worldIn, netHandler, statFile);
    }

    @Override
    public void sendChatMessage(String message) {

        if (message.startsWith(DarkForge.INSTANCE.commandPrefix)) {
            DarkForge.INSTANCE.commandManager.executeCommand(message.substring(DarkForge.INSTANCE.commandPrefix.length()));
        } else {
            super.sendChatMessage(message);
        }
    }
}
