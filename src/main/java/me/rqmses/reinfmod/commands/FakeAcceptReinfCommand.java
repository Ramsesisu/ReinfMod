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
public class FakeAcceptReinfCommand extends CommandBase implements IClientCommand {

    public static boolean isActive;

    @Override
    public String getName() {
        return "fakeacceptreinf";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "/fakeacceptreinf (f/d/gr/c/sms) (name) (distance)";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        isActive = true;
        EntityPlayerSP player = Minecraft.getMinecraft().player;

        player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "Die Fake-Reinforcementannahme wird get\u00E4tigt!"));

        final String[] Chat = {""};
        String name = "";
        final String[] number = new String[1];
        String distance = "";

        if (args.length >= 3) {
            switch (args[0]) {
                case "f":
                    Chat[0] = "/f";
                    break;
                case "d":
                    Chat[0] = "/d";
                    break;
                case "gr":
                    Chat[0] = "/gr";
                    break;
                case "c":
                    break;
                case "sms":
                    player.sendChatMessage("/nummer " + args[1]);
                    break;
                default:
                    player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakeacceptreinf f/d/gr/c/sms (name) (distance)"));
                    isActive = false;
                    return;
            }
            name = args[1];
            distance = args[2];
        } else {
            player.sendMessage(new TextComponentString(TextFormatting.DARK_GRAY + "[" + TextFormatting.DARK_GREEN + "ReinfMod" + TextFormatting.DARK_GRAY + "] " + TextFormatting.GREEN + "/fakeacceptreinf f/d/gr/c/sms name distance"));
            isActive = false;
            return;
        }
        if (Objects.equals(args[0], "sms")) {
            name = args[2];
            distance = args[3];
        }

        Timer timer = new Timer();
        final String[] finalChat = {Chat[0]};
        String finalName = name;
        String finalDistance = distance;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (Objects.equals(args[0], "sms")) {
                    number[0] = String.valueOf(NumberListener.lastCheckedNumber);
                    finalChat[0] = "/sms " + number[0] + " " + player.getName()+":";
                }
                player.sendChatMessage(finalChat[0] + " "+ finalName +", ich bin zu deinem Verst\u00E4rkungsruf unterwegs! ("+ finalDistance +" Meter entfernt)");
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
