package me.rqmses.reinfmod.commands;

import me.rqmses.reinfmod.listeners.NumberListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.IClientCommand;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Objects;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class FakeReinfCommand extends CommandBase implements IClientCommand {

    public static boolean isActive;

    @Override
    public String getName() {
        return "fakereinf";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fakereinf (f/d/gr/c/sms) (r/m/lb/e/da/ct/p/b/gn/t) (x) (y) (z)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        isActive = true;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "Das Fake-Reinforcement wird get\u00E4tigt!"));

        String Chat = "";
        String number;
        String Type = null;
        int locX = player.getPosition().getX();
        int locY = player.getPosition().getY();
        int locZ = player.getPosition().getZ();

        if (args.length >= 1) {
            switch (args[0]) {
                case "f":
                    Chat = "/f";
                    break;
                case "d":
                    Chat = "/d";
                    break;
                case "gr":
                    Chat = "/gr";
                    break;
                case "c":
                    break;
                case "sms":
                    player.sendChatMessage("/nummer " + args[1]);
                    number = String.valueOf(NumberListener.lastCheckedNumber);
                    Chat = "/sms " + number + " " + player.getName()+":";
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/reinforcement f/d/gr/c/sms (r/m/lb/d/da/ct/p/b/gn/t) (x) (y) (z)"));
                    isActive = false;
                    return;
            }
        }
        if (args.length >= 2 && !Objects.equals(args[0], "sms")) {
            switch (args[1]) {
                case "n":
                    Type = null;
                    break;
                case "r":
                    Type = "Rammen!";
                    break;
                case "m":
                    Type = "Medic ben\u00F6tigt!";
                    break;
                case "lb":
                    Type = "Leichenbewachung!";
                    break;
                case "e":
                    Type = "Dringend!";
                    break;
                case "da":
                    Type = "Drogenabnahme!";
                    break;
                case "ct":
                    Type = "Contract!";
                    break;
                case "p":
                    Type = "Plant!";
                    break;
                case "b":
                    Type = "Bombe!";
                    break;
                case "gn":
                    Type = "Geiselnahme!";
                    break;
                case "t":
                    Type = "Training!";
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakereinf f/d/gr/c/sms r/m/lb/d/da/ct/p/b/gn/t (x) (y) (z)"));
                    isActive = false;
                    return;
            }
        }
        if (args.length >= 5 && !Objects.equals(args[0], "sms")) {
            locX = Integer.parseInt(args[2]);
            locY = Integer.parseInt(args[3]);
            locZ = Integer.parseInt(args[4]);
        }

        if (Objects.equals(args[0], "sms")) {
            switch (args[2]) {
                case "n":
                    Type = null;
                    break;
                case "r":
                    Type = "Rammen!";
                    break;
                case "m":
                    Type = "Medic ben\u00F6tigt!";
                    break;
                case "lb":
                    Type = "Leichenbewachung!";
                    break;
                case "e":
                    Type = "Dringend!";
                    break;
                case "da":
                    Type = "Drogenabnahme!";
                    break;
                case "ct":
                    Type = "Contract!";
                    break;
                case "p":
                    Type = "Plant!";
                    break;
                case "b":
                    Type = "Bombe!";
                    break;
                case "gn":
                    Type = "Geiselnahme!";
                    break;
                case "t":
                    Type = "Training!";
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakereinf f/d/gr/c/sms r/m/lb/d/da/ct/p/b/gn/t (x) (y) (z)"));
                    isActive = false;
                    return;
            }
            if (args.length >= 6) {
                locX = Integer.parseInt(args[2]);
                locY = Integer.parseInt(args[3]);
                locZ = Integer.parseInt(args[4]);
            }
        }

        if (Type != null) {
            player.sendChatMessage(Chat + " "+Type);
        }
        player.sendChatMessage(Chat + " Ben\u00F6tige Verst\u00E4rkung! -> X: "+locX+" | Y: "+locY+" | Z: "+locZ);

        isActive = false;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public boolean allowUsageWithoutPrefix(ICommandSender sender, String message) {
        return false;
    }
}
