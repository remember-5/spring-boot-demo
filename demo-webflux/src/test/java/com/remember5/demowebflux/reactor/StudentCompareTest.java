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
package com.remember5.demowebflux.reactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author wangjiahao
 * @date 2023/12/21 12:28
 */
@SpringBootTest
public class StudentCompareTest {

    @Data
    @AllArgsConstructor
    @Accessors(chain = true)
    class Student {
        private int id;
        private String name;
        private double height;
        private double score;
    }

    class StudentIdComparator<S extends Student> implements Comparator<S> { // 2
        @Override
        public int compare(S s1, S s2) {
            return Integer.compare(s1.getId(), s2.getId());
        }
    }

    @Test
    public void test() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(10001, "张三", 1.73, 88));
        students.add(new Student(10002, "李四", 1.71, 96));
        students.add(new Student(10003, "王五", 1.85, 88));
        // 自己实现方式
        students.sort(new StudentIdComparator<>());
        // 匿名内部类
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o2.getScore(), o1.getScore());
            }
        });
        // lambda 表达式
        students.sort((s1, s2) -> Double.compare(s2.getScore(), s1.getScore()));
        System.err.println(students);
    }


}
