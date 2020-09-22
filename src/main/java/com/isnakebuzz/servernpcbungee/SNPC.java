package com.isnakebuzz.servernpcbungee;

import com.isnakebuzz.servernpcbungee.Listener.MessagesListener;
import net.md_5.bungee.api.plugin.Plugin;

public final class SNPC extends Plugin {
    @Override
    public void onEnable() {
        this.getProxy().getPluginManager().registerListener(this, new MessagesListener());
    }
}
