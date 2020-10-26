package com.isnakebuzz.servernpcbungee.Listener;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import com.isnakebuzz.servernpcbungee.Enums.SubChannel;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MessagesListener implements Listener {

    private String channelName = "servernpc:main";

    public MessagesListener() {
        ProxyServer.getInstance().registerChannel(channelName);
    }

    @SuppressWarnings("UnstableApiUsage")
    @EventHandler
    public void on(PluginMessageEvent event) {
        if (!event.getTag().equalsIgnoreCase(channelName)) {
            return;
        }

        ByteArrayDataInput in = ByteStreams.newDataInput(event.getData());
        String subChannel = in.readUTF();

        if (subChannel.equalsIgnoreCase(SubChannel.COMMAND.getName())) {

            boolean isConsole = isBool(in.readUTF());
            String playerName = in.readUTF();
            String message = in.readUTF();

            ProxiedPlayer player = ProxyServer.getInstance().getPlayer(playerName);
            if (player == null) return;

            if (isConsole) {
                ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), message.replaceAll("%player%", player.getName()));
            } else {
                ProxyServer.getInstance().getPluginManager().dispatchCommand(player, message);
            }

        }
    }

    private boolean isBool(String s) {
        boolean bool;
        try {
            bool = Boolean.parseBoolean(s);
        } catch (Exception ignored) {
            bool = false;
        }

        return bool;
    }
}