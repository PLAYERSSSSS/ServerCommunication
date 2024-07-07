package org.example.passageway.serverpassageway;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @JdkVersion:  11
 * @author Liu
 */
public final class ServerPassageway extends JavaPlugin {
    public static String url;
    public static String ServerName;
    public static String ChannelName;
    public static String Token;
    public static String FString;
    public static Client client;
    @SuppressWarnings("AlibabaThreadPoolCreation")
    private static final ExecutorService THREAD_POND = Executors.newSingleThreadExecutor();


    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new Monitor(), this);
        saveDefaultConfig();
        url = (String) getConfig().get("URL");
        ServerName = (String) getConfig().get("server_Name");
        ChannelName = (String)getConfig().get("ChannelName");
        Token = (String)getConfig().get("Token");
        FString = (String)getConfig().get("format");
        THREAD_POND.submit(new WebSocketClient(url));
    }

    @Override
    public void onDisable() {
        client.close(1000);
    }
}
