package com.api.Utils.File;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class file_Folder {
    /**
     * @param path: 文件路径
     * @apiNote 根据路径判断文件夹是否存在
     * @return boolean
     */
    public static boolean exists(String path) {
        java.io.File file = new java.io.File(path);
        return file.exists() || file.isDirectory();
    }
    /**
     * @param path: 文件路径
     * @apiNote 根据路径创建文件夹
     * @return void
     */
    public static void addmk(String path) {
        if (!exists(path)) {
            java.io.File file = new java.io.File(path);
            file.mkdir();
        }
    }
    /**
     * @param path: 文件夹路径
     * @apiNote 根据路径获取文件夹下所有文件
     * @return List<java.io.File>
     */
    public static List<File> getAllFile(String path) {
        List<java.io.File> list = new ArrayList<>();
        java.io.File dirFile = new java.io.File(path);
        if (!dirFile.exists() || dirFile.isFile())
            return null;

        java.io.File[] childrenFiles = dirFile.listFiles();
        if (Objects.isNull(childrenFiles) || childrenFiles.length == 0)
            return null;
        for (java.io.File childFile : childrenFiles) {
            // 如果是文件，直接添加到结果集合
            if (childFile.isFile()) {
                list.add(childFile);
            }
        }
        return list;
    }
}
