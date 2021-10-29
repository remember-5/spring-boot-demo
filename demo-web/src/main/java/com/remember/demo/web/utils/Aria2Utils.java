package com.remember.demo.web.utils;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.remember.demo.web.entity.Aria2Json;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

/**
 * aria2请求的Json对象
 *
 * @author wangjiahao
 * @date 2021/10/29
 */
@Slf4j
public class Aria2Utils {

    public static void setStatus() {
        Aria2Json objectAria2Json = new Aria2Json()
                .setId(Aria2Json.UUID_STR)
                .setJsonrpc(Aria2Json.JSON_RPC)
                .setMethod(Aria2Json.METHOD_GET_GLOBAL_STAT)
                .setParams(Collections.singletonList("token:" + Aria2Json.SECRET));
        HttpResponse execute = HttpRequest
                .post(Aria2Json.ARIA2_URL)
                .body(JSON.toJSONString(objectAria2Json), ContentType.JSON.toString())
                .execute();
//        System.err.println(execute.body());
    }


    public static boolean downloadFiles(String downloadUrl, String downloadName) {
        setStatus();
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("token:" + Aria2Json.SECRET);
        objects.add(Collections.singletonList(downloadUrl));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("out", downloadName);
        objects.add(jsonObject);

        Aria2Json objectAria2Json = new Aria2Json()
                .setId(Aria2Json.UUID_STR)
                .setJsonrpc(Aria2Json.JSON_RPC)
                .setMethod(Aria2Json.METHOD_ADD_URI)
                .setParams(objects);

        HttpResponse execute = HttpRequest.post(Aria2Json.ARIA2_URL).body(JSON.toJSONString(objectAria2Json), ContentType.JSON.toString()).execute();
//        System.err.println(execute.body());
        return execute.isOk();
    }

    public static void main(String[] args) {
        String downloadUrl = "http://v5-g.douyinvod.com/49f80dc558bc647a57561dbaf3963d26/617b6962/video/tos/cn/tos-cn-ve-15-alinc2/1f802f5eea93466db63513ffefb94427/?a=1128&br=1698&bt=1698&cd=0%7C0%7C0&ch=0&cr=0&cs=0&cv=1&dr=0&ds=3&er=&ft=OR_LrKZZI02K1iTz7Th94ictRsWd.Hs2_68&l=202110291023530102120381091A319917&lr=&mime_type=video_mp4&net=0&pl=0&qs=0&rc=ajo0OGY6ZjR2ODMzNGkzM0ApM2ZlPDc2NGU1NzU4NzhoaGcpaGRqbGRoaGRmNWJjcHI0X3FrYC0tZC1hc3NiYDAzYi0uYS4zNGEvYzAyOmNwb2wrbStqdDo%3D&vl=&vr=";
        String downloadName = "java-test.mp4";
        downloadFiles(downloadUrl, downloadName);
    }


}
