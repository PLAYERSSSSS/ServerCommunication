package org.example.passageway.serverpassageway;

import com.api.Utils.Json.Json;
import com.api.Utils.String.StringU;
import org.bukkit.Bukkit;
import org.example.passageway.serverpassageway.utils.CacheConsts;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/***
 * @author Liu
 */
public class Client extends WebSocketClient {
    Logger logger = Bukkit.getLogger();

    public Client(String url){
        super(URI.create(url));

        // 登录凭证
        Base64.Encoder encoder = Base64.getEncoder();
        this.addHeader("Protocol","SuperLink-v4@SuperScript");
        if (CacheConsts.Config.DEFAULT.equals(ServerPassageway.ChannelName)){
            this.addHeader("ChannelName", encoder.encodeToString(CacheConsts.Config.DEFAULT_CHANNEL_NAME.getBytes(StandardCharsets.UTF_8)));
        }else{
            this.addHeader("ChannelName", encoder.encodeToString(ServerPassageway.ChannelName.getBytes(StandardCharsets.UTF_8)));
        }
        this.addHeader("ChannelToken", encoder.encodeToString(ServerPassageway.Token.getBytes(StandardCharsets.UTF_8)));
        this.addHeader("ServerName", encoder.encodeToString(ServerPassageway.ServerName.getBytes(StandardCharsets.UTF_8)));
        ServerPassageway.client = this;
        this.connect();
    }

    /***
     * 连接建立成功时调用该方法
     * @param serverHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("成功与互通产生连接");
    }

    /***
     * //收到来自服务端的消息时调用该方法
     * @param s
     */
    @Override
    public void onMessage(String s) {
        // 将消息转为Map
        Map data = Json.loads(s);
        Map content = Json.loads(String.valueOf(data.get("Content")));
        switch (String.valueOf(data.get("Type"))){
            case "server.auth_success":
                if(content.containsKey(CacheConsts.DataPackage.Type.MEMBER_COUNT)){
                    logger.info(String.format("互通服务器验证通过 当前有%s个服务器在线",content.get("Member_count")));
                }else{
                    logger.warning(String.format("互通服务器验证被拒 原因:%s",content.get("Reason")));
                }
                break;

            case "server.kick":
                logger.warning(String.format("被踢出互通服务器 原因:%s",content.get("Reason")));
                break;

            case "chat.msg":
                Map<String,String> map = new HashMap<>(3);
                map.put("serverName",String.valueOf(content.get("Sender")));
                map.put("playerName",String.valueOf(content.get("ChatName")));
                map.put("mess",String.valueOf(content.get("Msg")));
                Bukkit.broadcastMessage(StringU.f_Strings(ServerPassageway.FString,map));
                break;
            default:
                break;
        }
    }

    /***
     * 连接关闭时调用该方法
     * @param code
     * @param s
     * @param b
     */
    @Override
    public void onClose(int code, String s, boolean b) {
        if(code == CacheConsts.WebSocketCode.NORMAL_EXIT){
            logger.info("与互通连接关闭");
        } else if (code == CacheConsts.WebSocketCode.CONNECTION_CLOSE_EXCEPTION || code == CacheConsts.WebSocketCode.ABNORMAL_CLOSE) {
            logger.warning("与互通连接意外断开");
            for (int i = 0; i < CacheConsts.Config.RECONNECTION_TIMES; i++) {
                logger.info(String.format("第%s次尝试重连",i));
                try {
                    this.reconnect();
                }catch (IllegalStateException e){
                    logger.info(String.format("第%s次尝试重连 失败",i));
                }
            }
        }
    }

    /***
     * 出现错误时调用该方法
     * @param e
     */
    @Override
    public void onError(Exception e) {
        logger.warning(e.getMessage());
    }
}
