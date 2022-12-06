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
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.collection.parallel.ParIterableLike;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber
public class FakeSLocCommand extends CommandBase implements IClientCommand {

    public static boolean isActive;

    @Override
    public String getName() {
        return "fakesloc";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fakesloc (f/sf/d/gr/grl/c/w/me/sms) (name) (x) (y) (z)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        isActive = true;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "Der Fake-SLoc wird get\u00E4tigt!"));

        final String[] Chat = {""};
        String name;
        final String[] number = new String[1];
        int locX = player.getPosition().getX();
        int locY = player.getPosition().getY();
        int locZ = player.getPosition().getZ();

        if (args.length < 2) {
            player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakesloc f/sf/d/gr/grl/c/w/me/sms (name) (x) (y) (z)"));
            return;
        }

        if (args.length >= 2) {
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
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakesloc f/sf/d/gr/grl/c/w/me/sms (name) (x) (y) (z)"));
                    isActive = false;
                    return;
            }
            name = args[1];
        } else {
            player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakesloc f/sf/d/gr/grl/c/w/me/sms name (x) (y) (z)"));
            isActive = false;
            return;
        }
        if (args.length >= 5) {
            locX = Integer.parseInt(args[2]);
            locY = Integer.parseInt(args[3]);
            locZ = Integer.parseInt(args[4]);
        }

        Timer timer = new Timer();
        final String[] finalChat = {Chat[0]};
        int finalLocX = locX;
        int finalLocY = locY;
        int finalLocZ = locZ;
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
                player.sendChatMessage(finalChat[0] + " Positionsteilung f\u00FCr "+name+"! -> X: "+ finalLocX +" | Y: "+ finalLocY +" | Z: "+ finalLocZ);
                isActive = false;
            }
        }, 250L);
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
