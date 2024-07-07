package org.example.passageway.serverpassageway;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.example.passageway.serverpassageway.utils.CraftDataPackage;

/***
 * @author Liu
 */
public class Monitor implements Listener {
    public Monitor(){}

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event){
        // 通过工具类WSConnectOutput 传递事件
        CraftDataPackage.sendPlayerChat(event.getPlayer().getName(), event.getMessage());
    }
}
