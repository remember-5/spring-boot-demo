package com.remember.postgresql.runner;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangjihao
 * @date
 */
@Component
@RequiredArgsConstructor
public class Runner implements ApplicationRunner {

    PersonMapper personMapper;

    @Override
    public void run(ApplicationArguments args) {
        Person person = personMapper.selectById(1);
        System.err.println(person);
    }
}
