package me.rqmses.reinfmod.listeners;


import me.rqmses.reinfmod.commands.FakeAcceptReinfCommand;
import me.rqmses.reinfmod.commands.FakeReinfCommand;
import me.rqmses.reinfmod.commands.FakeSLocCommand;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberListener {

    public static final Pattern NUMBER_PATTERN = Pattern.compile("^Nummer von (?:\\[UC])*\\w+: (\\d+)$");
    public static int lastCheckedNumber = 0;

    @SubscribeEvent
    public void onClientChatReceived(ClientChatReceivedEvent e) {
        String msg = e.getMessage().getUnformattedText();

        Matcher numberMatcher = NUMBER_PATTERN.matcher(msg);
        if (numberMatcher.find()) {
            lastCheckedNumber = Integer.parseInt(numberMatcher.group(1));
            if (FakeReinfCommand.isActive || FakeAcceptReinfCommand.isActive || FakeSLocCommand.isActive) {
                e.setCanceled(true);
                FakeReinfCommand.isActive = FakeAcceptReinfCommand.isActive = FakeSLocCommand.isActive = false;
            }
        }
    }
}
