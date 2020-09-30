package com.remember.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remember.mybatisplus.entity.Person;
import com.remember.mybatisplus.mapper.PersonMapper;
import com.remember.mybatisplus.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wangjiahao
 */
@Slf4j
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        log.info("开始执行事物");
        try {
            // TODO 先测试写入操作
            Person person = new Person(1, null, 24, "PVG airport", null, null);
            this.save(person);
        } catch (Exception e) { // TODO 抛出异常
            // TODO 异常块内调用三方模块
            myPrint();
            new Exception("hhhh");
        }
    }

    private void myPrint() {
        log.info("My Print");
    }
}

