/**
 * Copyright [2022] [remember5]
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.remember5.demopackage.runner;

import com.remember5.demopackage.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjiahao
 * @date 2023/12/27 12:25
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class CustomRunner implements CommandLineRunner {

    private final PersonService personService;

    @Override
    public void run(String... args) throws Exception {
        personService.list().forEach(person -> log.debug("person : {}", person));
    }
}
