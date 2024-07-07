package com.api.Utils.File;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class File {

    /**
     * @param path: 文件路径
     * @apiNote 根据路径读取文件
     * @return String
     */
    public static String read(String path) {
        String te = "";
        try {

            java.io.File file = new java.io.File(path);
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    throw new Exception();
                }
            }

            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null) {
                te = te.concat(line);
            }
            br.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (te.hashCode() == 0) {
            te = null;
        }
        return te;
    }

    /**
     * @param path: 文件路径
     * @param text: 写入的内容
     * @apiNote 写入文件内容
     * @return boolean
     */
    public static boolean write(String path, String text) {
        boolean bl = true;
        try {
            FileWriter writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);
            pw.print(""); // 清空文件内容
            pw.println(text);
            pw.flush(); // 写入新的文件内容

            pw.close();
            writer.close();
        } catch (Exception e) {
            bl = false;
        }
        return bl;
    }

    /**
     * @param path: 文件路径
     * @apiNote 判断路径是否存在
     * @return boolean
     */
    public static boolean exists(String path) {
        java.io.File file = new java.io.File(path);
        return file.exists();
    }

    /**
     * @param path: 文件路径
     * @apiNote 创建文件
     * @return boolean
     */
    public static boolean creat(String path) throws IOException {
        return new java.io.File(path).createNewFile();
    }

    /**
     * @param filePath: 需要计算的文件路径
     * @apiNote 计算文件MD5值
     * @return String
     */
    public static String calculateMD5(String filePath) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            FileInputStream fis = new FileInputStream(filePath);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            fis.close();
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
