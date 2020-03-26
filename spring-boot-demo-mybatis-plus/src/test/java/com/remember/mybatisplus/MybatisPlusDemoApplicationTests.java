package com.remember.mybatisplus;

import com.remember.mybatisplus.entity.HotWireKb;
import com.remember.mybatisplus.service.HotWireKbService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MybatisPlusDemoApplicationTests {

	@Autowired
	private HotWireKbService hotWireKbService;

	@Test
	void contextLoads() {
		List<HotWireKb> list = hotWireKbService.list();
		System.err.println(list.size());
		System.err.println(list.get(1));
	}

}
