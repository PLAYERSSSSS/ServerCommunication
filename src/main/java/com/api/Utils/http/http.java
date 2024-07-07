package com.api.Utils.http;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class http {
    /**
     * @param Url: 请求的URL地址
     * @param code: 解析时的编码 例如: "UTF-8"
     * @apiNote 向目标URL发起GET请求
     * @return String
     */
    public static String Get_URL_content(String Url,String code) {
        //请求的url
        URL url;
        //请求的输入流
        BufferedReader in = null;
        //输入流的缓冲
        StringBuilder sb = new StringBuilder();
        try {
            url = new URL(Url);
            in = new BufferedReader(new InputStreamReader(url.openStream(), code));
            String str;
            //一行一行进行读入
            while ((str = in.readLine()) != null) {
                sb.append(str);
            }
        } catch (Exception ignored) {
        } finally {
            try {
                if (in != null) {
                    in.close(); //关闭流
                }
            } catch (IOException ignored) {
            }
        }
            return sb.toString();
    }
    /**
     * @param Url: 请求的URL地址
     * @param savePath: 保存完整路径
     * @apiNote 从目标Url下载文件
     * @return void
     */
    public static void downloadFile(String Url,String savePath) throws IOException {

        URL url = new URL(Url);
        URLConnection conn = url.openConnection();
        BufferedInputStream inputStream = new BufferedInputStream(conn.getInputStream());
        FileOutputStream outputStream = new FileOutputStream(savePath);
        byte[] buffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.close();
        inputStream.close();
    }
}
