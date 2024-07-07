package org.example.passageway.serverpassageway.utils;

import com.api.Utils.String.StringU;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/***
 * @author Liu
 * 用于产生数据包
 */
public class CraftPackage {
    public static final String CHAT_NAME = "ChatName";
    public static final String TYPE = "Type";
    public static final String CONTENT = "Content";
    public static final String MSG = "Msg";
    public static final String TYPE_CHAT_MSG = "chat.msg";
    private CraftPackage(){}

    /***
     * 用于产生玩家消息数据包
     */
    public static byte[] newPlayerChatPackage(String player,String mess){
        /*
         数据包格式
         */
        Map<String,String> map = new HashMap<>(2);
        map.put(TYPE, TYPE_CHAT_MSG);
        map.put(CONTENT,String.format("{\"%s\":\"%s\",\"%s\":\"%s\"}", MSG,mess, CHAT_NAME,player));
        // Content是一个Map<String,String> 所以在格式化字符串内部无需用{}包括
        return StringU.f_Strings("{\"Type\":\"%%Type%%\",\"Content\":%%Content%%}",map).getBytes(StandardCharsets.UTF_8);
    }
}
