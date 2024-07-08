 /*
  * Created on 2024-1-10
  * File name StringU.java
  * aim expand the function of string message
  */
 package com.api.Utils.String;

 import java.util.Map;
 import java.util.regex.Matcher;
 import java.util.regex.Pattern;

 /**
  * 与字符串有关的接口
  * @author Liu
  */
 public class StringU {
     private StringU(){}
     /**
      * <p>采用类Python f-String的方式产生格式化字符串</p>
      * <p>示例:</p>
      * {@literal Map map = new HashMap<String,String>();}<br>
      * map.put("Name","GG boy");<br>
      * f_String("i %%Name%%",map)<br>
      *
      * @param text 需要被格式化的字符串
      * @param map  字符串中的变量的集合
      */
     public static String f_Strings(String text, Map<String, String> map) {
         String[] str = text.concat("\n").split("%%");
         if (str.length % 2 != 0) {
             for (int i = 0; i < str.length - 1; i++) {
                 if (i % 2 != 0) {
                     str[i] = map.get(str[i]);
                 }
             }
             String re = "";
             for (String i : str) {
                 re = re.concat(i);
             }
             return re.substring(0, re.length() - 1);
         } else {
             throw new RuntimeException("Non conforming F-String");
         }
     }
     /**
      * 计算一个字符串在另一个字符串中出现的次数
      * @param text 主字符串
      * @param str 需要被计算在主字符串中出现次数的字符串
      */
     public static int getOccurrenceNumber(String text,String str){
         int number = 0;
         int size = str.length();
         for(int i=0;i<text.length();i++){
             System.out.println(0);
             if(i+size > text.length()){
                 break;
             }
             int x = (text.substring(i,i + size).equals(str))? 1:0;
             number += x;
         }
         return number;
     }
     /**
      * 判断字符串类型的手机号是否合法
      * @param phone 字符串类型的手机号
      * */
     public static boolean isMobilePhoneNumber(String phone) {
         String regex = "^((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(166)|(17[0,135678])|(18[0-9])|(19[8|9]))\\d{8}$";
         if (phone.length() != 11) {
             return false;
         } else {
             Pattern p = Pattern.compile(regex);
             Matcher m = p.matcher(phone);
             return m.matches();
         }
     }
 }
