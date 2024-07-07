package org.example.passageway.serverpassageway.utils;

/***
 * 常量枚举类
 * @author Liu
 */
public class CacheConsts {
    private CacheConsts(){}
    public static class Config {
        private Config(){}
        public final static int RECONNECTION_TIMES = 3;
        // 配置文件中默认频道名称
        public final static String DEFAULT = "default";
        // 配置文件中默认频道名称在远程服务器上的实际名称
        public final static String DEFAULT_CHANNEL_NAME = "公共大区";
    }
    public static class WebSocketCode {
        private WebSocketCode(){}
        //正常关闭
        public static final int NORMAL_EXIT = 1000;
        //终端离开
        public static final int TERMINAL_DEPARTURE = 1001;
        //协议错误
        public static final int PROTOCOL_ERROR = 1002;
        //数据类型错误
        public static final int DATA_TYPE_ERROR = 1003;
        //无法接收
        public static final int UNABLE_TO_RECEIVE = 1005;//
        //连接关闭异常
        public static final int CONNECTION_CLOSE_EXCEPTION = 1006;//
        //服务器遇到异常
        public static final int THE_SERVER_ENCOUNTERED_AN_EXCEPTION = 1011;
        //异常关闭
        public static final int ABNORMAL_CLOSE = 1012;
    }
    public static class DataPackage {
        private DataPackage(){}
        public class Type {
            // 频道中客户端数量
            public static final String MEMBER_COUNT = "Member_count";
        }
    }
}
