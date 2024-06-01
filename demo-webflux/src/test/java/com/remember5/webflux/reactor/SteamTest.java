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
package com.remember5.webflux.reactor;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * @author wangjiahao
 * @date 2023/12/21 12:38
 */
@Slf4j
@SpringBootTest
public class SteamTest {


    @Test
    public void test() {
        // Mono 只有一个元素
        Mono.just("hello").subscribe(System.out::println);
        log.info("==================================================");
        // Flux 可以有多个元素
        Flux.fromArray(Arrays.array(1, 2, 3, 4, 5, 6)).subscribe(System.out::println);
        log.info("==================================================");
        Flux.just(1, 2, 3, 4, 5, 6).subscribe(System.out::println, System.err::println, () -> System.out.println("complete"));
        log.info("==================================================");
        Mono.just(new Exception("error")).subscribe(System.out::println, System.err::println, () -> System.out.println("complete"));
    }

    /**
     * StepVerifier
     */
    @Test
    public void test1() {
        // expectNext用于测试下一个期望的数据元素，expectErrorMessage用于校验下一个元素是否为错误信号，expectComplete用于测试下一个元素是否为完成信号。
        StepVerifier.create(Flux.just(1, 2, 3, 4, 5, 6)).expectNext(1, 2, 3, 4, 5, 6).expectComplete().verify();
        log.info("==================================================");

        StepVerifier.create(Mono.error(new Exception("error"))).expectErrorMessage("error").verify();

        log.info("==================================================");
    }


    @Test
    public void test2() {
        StepVerifier.create(Flux.range(1, 6).map(i -> i * i)).expectNext(1, 4, 9, 16, 25, 36).verifyComplete();

        log.info("==================================================");


        StepVerifier.create(
                Flux.just("flux", "mono")
                        .flatMap(s -> Flux.fromArray(s.split("\\s*"))
                                .delayElements(Duration.ofMillis(100)))
                        .doOnNext(System.out::println))
                .expectNextCount(8)
                .verifyComplete();
    }


}
