package org.example.passageway.serverpassageway.utils;

import org.example.passageway.serverpassageway.Client;
import org.example.passageway.serverpassageway.ServerPassageway;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/***
 * @author Liu
 * 关于从Bukkit到互通的构造与发送数据包的工具类
 */
public class CraftDataPackage {
    private static final BlockingQueue<Runnable> QUEUE = new LinkedBlockingQueue<>();
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(1,
            1, 60L, TimeUnit.SECONDS, QUEUE, r -> {
        Thread thread = new Thread(r);
        thread.setName(String.format("%s-thread pool", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date())));
        return thread;
    });
    private CraftDataPackage(){}

    public static void sendPlayerChat(String name,String mess){
        THREAD_POOL_EXECUTOR.submit(() -> {
            Client client = ServerPassageway.client;
            client.send(CraftPackage.newPlayerChatPackage(name,mess));
        });
    }
}
