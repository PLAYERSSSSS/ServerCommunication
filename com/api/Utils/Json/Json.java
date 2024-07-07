package com.api.Utils.Json;

import com.alibaba.fastjson.JSON;
import java.util.HashMap;
import java.util.Map;
public class Json {
    /**
     * @param json: 字符串
     * @return Map
     * @apiNote 解析Json字符串为 Map集合
     */
    public static HashMap loads(String json){
        return JSON.parseObject(json, HashMap.class);
    }
    /**
     * @param map: 字符串
     * @apiNote 解析Map集合为 Json字符串
     * @return String
     */
    public static String dumps(Map map) {
        return JSON.toJSONString(map);
    }
}
