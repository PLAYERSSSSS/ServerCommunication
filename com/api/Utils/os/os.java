package com.api.Utils.os;

import java.text.SimpleDateFormat;
import java.util.Date;
public class os {
    /**
     * @apiNote 获取当前系统名称
     * @return String
     */
    public static String get_System_name(){
        return System.getProperty("os.name");
    }
    /**
     * @apiNote 获取当前系统版本
     * @return String
     */
    public static String getSystemVersion(){
        return System.getProperty("os.version");
    }
    /**
     * @apiNote 获取当前系统架构
     * @return String
     */
    public static String getSystemArch(){
        return System.getProperty("os.arch");
    }
    /**
     * @apiNote 获取当前系统格式化时间
     * @return String
     */
    public static String GetTime(){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(day);
    }
    /**
     * @param pattern: 指定格式化的形式<br>例: "yyyy-MM-dd HH:mm:ss"
     * @apiNote 获取指定的系统格式化时间
     * @return String
     */
    public static String GetTime(String pattern){
        Date day = new Date();
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(day);
    }
}

