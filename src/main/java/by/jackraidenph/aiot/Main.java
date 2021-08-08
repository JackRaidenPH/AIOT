package by.jackraidenph.aiot;

import by.jackraidenph.aiot.items.Registry;
import net.minecraftforge.fml.common.Mod;

@Mod(Main.MOD_ID)
public class Main {

    public static final String MOD_ID = "aiot";

    public Main() {
        Registry.init();
    }
}
