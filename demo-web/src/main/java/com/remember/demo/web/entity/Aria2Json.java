package com.remember.demo.web.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangjiahao
 * @date 2021/10/29
 */
@Data
@Accessors(chain = true)
public class Aria2Json {

    /**
     * 方法名常量
     */
    public final static String METHOD_TELL_ACTIVE = "aria2.tellActive";
    public final static String METHOD_ADD_URI = "aria2.addUri";
    public final static String METHOD_GET_GLOBAL_STAT = "aria2.getGlobalStat";
    public final static String METHOD_TELL_STOPPED = "aria2.tellStopped";
    public final static String METHOD_TELL_WAITING = "aria2.tellWaiting";
    public final static String METHOD_REMOVE_DOWNLOAD_RESULT = "aria2.removeDownloadResult";
    public final static String[] PARAM_ARRAY_OF_FILED =
            new String[]{"totalLength", "completedLength", "files", "status", "errorCode", "gid"};

    public static final String SECRET = "";
    public static final String ARIA2_URL = "http://1.15.94.39:6800/jsonrpc";
    public static final String JSON_RPC = "2.0";
    public static final String UUID_STR = "MY_JAVA_CONNECT";

    private String id;

    private String  jsonrpc;

    private String method;

    private Object params;


}
