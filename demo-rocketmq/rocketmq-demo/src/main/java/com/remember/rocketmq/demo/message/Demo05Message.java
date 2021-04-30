package com.remember.rocketmq.demo.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wangjihao
 * @date 2020/12/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Demo05Message {
    public static final String TOPIC = "DEMO_05";

    /**
     * 编号
     */
    private Integer id;
}
