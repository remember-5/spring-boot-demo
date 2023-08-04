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
package com.remember5.design.pattern.singleton;

/**
 * @author wangjiahao
 * @date 2023/8/4 15:12
 */
public class SingletonPatternDemo {
    public static void main(String[] args) {
        // 写法错误 构造函数 SingleObject() 是不可见的
        //SingleObject object = new SingObject();
        // 获取唯一可用的对象
        final SingleObject instance = SingleObject.getInstance();
        instance.showMessage();
    }
}
