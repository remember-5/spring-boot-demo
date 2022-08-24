package com.remember5.websocket.client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjiahao
 * @date 2022/7/4 10:45
 */
public class ClientUtils {
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        Map<String, String> httpHeaders = new HashMap<String, String>();

        httpHeaders.put("Username","");
        httpHeaders.put("Password","");

        Client2 client2 = new Client2(new URI("ws://localhost:9999"), httpHeaders);
        client2.connect();

        while (true) {
            if (client2.isOpen()) {
                client2.send("Hello");
            }
            Thread.sleep(1000);
        }


    }
}
