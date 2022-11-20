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
        return "/fakesloc (f/d/gr/c/sms) (name) (x) (y) (z)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        isActive = true;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "Der Fake-SLoc wird get\u00E4tigt!"));

        String Chat = "";
        String name;
        String number;
        int locX = player.getPosition().getX();
        int locY = player.getPosition().getY();
        int locZ = player.getPosition().getZ();

        if (args.length >= 2) {
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
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakesloc f/d/gr/c/sms (name) (x) (y) (z)"));
                    isActive = false;
                    return;
            }
            name = args[1];
        } else {
            player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakesloc f/d/gr/c/sms name (x) (y) (z)"));
            isActive = false;
            return;
        }
        if (args.length >= 5) {
            locX = Integer.parseInt(args[2]);
            locY = Integer.parseInt(args[3]);
            locZ = Integer.parseInt(args[4]);
        }

        player.sendChatMessage(Chat + " Positionsteilung f\u00FCr "+name+"! -> X: "+locX+" | Y: "+locY+" | Z: "+locZ);

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
