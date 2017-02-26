import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;

@Module.ModInfo(name = "AutoSprint", description = "Automatically Sprints for you.", category = Module.Category.MOVEMENT, bind = Keyboard.KEY_S)
public class Step extends Module {
	
	private float stepHeight = 1.5f; /** One and a half block */

	@Override
	@SideOnly(Side.CLIENT)
	public void onEnable() {
		getPlayer().stepHeight = stepHeight;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void onDisable() {
		getPlayer().stepHeight = 0.5f;
	}
	    
    public EntityPlayerSP getPlayer()
    {
    	return Minecraft.getMinecraft().player;
    }
}
