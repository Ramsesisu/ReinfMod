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
import java.util.Timer;
import java.util.TimerTask;

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
        return "/fakereinf (f/sf/d/gr/grl/c/w/me/sms) (r/m/lb/d/da/ct/p/b/gn/t) (x) (y) (z)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        isActive = true;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "Das Fake-Reinforcement wird get\u00E4tigt!"));

        final String[] Chat = {""};
        final String[] number = new String[1];
        String Type = null;
        int locX = player.getPosition().getX();
        int locY = player.getPosition().getY();
        int locZ = player.getPosition().getZ();

        if (args.length == 0) {
            player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakereinf f/sf/d/gr/grl/c/w/me/sms r/m/lb/d/da/ct/p/b/gn/t (x) (y) (z)"));
            return;
        }

        if (args.length >= 1) {
            switch (args[0]) {
                case "f":
                    Chat[0] = "/f";
                    break;
                case "sf":
                    Chat[0] = "/sf";
                    break;
                case "d":
                    Chat[0] = "/d";
                    break;
                case "gr":
                case "grl":
                    Chat[0] = "/gr";
                    break;
                case "c":
                    break;
                case "w":
                    Chat[0] = "/w";
                    break;
                case "me":
                    Chat[0] = "/me";
                    break;
                case "sms":
                    player.sendChatMessage("/nummer " + args[1]);
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/reinforcement f/sf/d/gr/grl/c/w/me/sms (r/m/lb/d/da/ct/p/b/gn/t) (x) (y) (z)"));
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
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakereinf f/sf/d/gr/grl/c/w/me/sms r/m/lb/d/da/ct/p/b/gn/t (x) (y) (z)"));
                    isActive = false;
                    return;
            }
            if (args.length >= 5) {
                locX = Integer.parseInt(args[2]);
                locY = Integer.parseInt(args[3]);
                locZ = Integer.parseInt(args[4]);
            }
        }

        if (Objects.equals(args[0], "sms")) {
            if (args.length >= 3) {
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
                        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakereinf f/sf/d/gr/grl/c/w/me/sms r/m/lb/d/da/ct/p/b/gn/t (x) (y) (z)"));
                        isActive = false;
                        return;
                }
                if (args.length >= 6) {
                    locX = Integer.parseInt(args[3]);
                    locY = Integer.parseInt(args[4]);
                    locZ = Integer.parseInt(args[5]);
                }
            }
        }

        Timer timer = new Timer();
        String finalType = Type;
        final String[] finalChat = {Chat[0]};
        int finalLocX = locX;
        int finalLocY = locY;
        int finalLocZ = locZ;

        long timer1 = 250L;
        long timer2 = 250L;
        if (Objects.equals(args[0], "sms") || Objects.equals(args[0], "gr") || Objects.equals(args[0], "grl") || Objects.equals(args[0], "w") || Objects.equals(args[0], "me")) {
            if (finalType != null) {
                timer1 = 500L;
                timer2 = 1400L;
            }
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Objects.equals(args[0], "gr") || Objects.equals(args[0], "w") || Objects.equals(args[0], "me")) {
                    finalChat[0] = finalChat[0] + " " + player.getName() + ":";
                }
                if (Objects.equals(args[0], "sms")) {
                    number[0] = String.valueOf(NumberListener.lastCheckedNumber);
                    finalChat[0] = "/sms " + number[0] + " " + player.getName()+":";
                }
                if (finalType != null) {
                    player.sendChatMessage(finalChat[0] + " "+ finalType);
                }
            }
        }, timer1);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                player.sendChatMessage(finalChat[0] + " Ben\u00F6tige Verst\u00E4rkung! -> X: "+ finalLocX +" | Y: "+ finalLocY +" | Z: "+ finalLocZ);
                isActive = false;
            }
        }, timer2);
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
