package com.api.Utils.Path;

import java.io.File;

public class Path {
    private String separator = File.separator;

    /**
     * @param path: 根据访问路径顺序生成的String[]
     *            <br>例如: new String[]{"C:","Users","a","b","c.java"}
     *            <br>return : C:\\Users\\a\\b\\c.java (Windows)
     *            <br>return : C:/Users/a/b/c.java (Linux)
     * @apiNote 获取当前系统生成路径
     * @return String
     */
    public static String get_The_path(String[] path){
        String re = "";
        for (String join : path){
            re = re.concat(join).concat(File.separator);
        }
        if(re.hashCode() == 0){
            return null;
        }
        return re.substring(0,re.length() - 1);
    }
}
