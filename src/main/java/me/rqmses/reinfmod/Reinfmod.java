package me.rqmses.reinfmod;

import me.rqmses.reinfmod.commands.FakeAcceptReinfCommand;
import me.rqmses.reinfmod.commands.FakeReinfCommand;
import me.rqmses.reinfmod.commands.FakeSLocCommand;
import me.rqmses.reinfmod.listeners.NumberListener;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(
        modid = Reinfmod.MOD_ID,
        name = Reinfmod.MOD_NAME,
        version = Reinfmod.VERSION
)
public class Reinfmod {

    public static final String MOD_ID = "reinfmod";
    public static final String MOD_NAME = "ReinfMod";
    public static final String VERSION = "1.0";

    @Mod.Instance(MOD_ID)
    public static Reinfmod INSTANCE;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new NumberListener());
        ClientCommandHandler.instance.registerCommand(new FakeReinfCommand());
        ClientCommandHandler.instance.registerCommand(new FakeAcceptReinfCommand());
        ClientCommandHandler.instance.registerCommand(new FakeSLocCommand());
    }
}
