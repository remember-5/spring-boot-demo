package com.remember.mybatisplus;

import com.remember.mybatisplus.entity.HotWireKbTest;
import com.remember.mybatisplus.service.HotWireKbService;
import com.remember.mybatisplus.service.HotWireKbServiceTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisPlusDemoApplicationTests {
    @Autowired
    private HotWireKbService hotWireKbService;

    @Autowired
    private HotWireKbServiceTest hotWireKbServiceTest;

    @Test
    void contextLoads() {
//		List<HotWireKb> list = hotWireKbService.list();
//		System.err.println(list.size());

        List<HotWireKbTest> list1 = hotWireKbServiceTest.list();
        System.err.println(list1.size());

        int i = 0;
        while (i <= 100) {
            list1.forEach(e -> {
                hotWireKbServiceTest.saveBatch(list1);
            });
            System.err.println(i);
            ++i;
        }
    }

    @Test
    void query1() {
        String query = "植保";
        List<HotWireKbTest> list = hotWireKbServiceTest.lambdaQuery().eq(HotWireKbTest::getAttrsSecondTag, query).list();
        System.err.println(list.size());
    }


}
