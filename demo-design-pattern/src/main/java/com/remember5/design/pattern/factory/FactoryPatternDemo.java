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
package com.remember5.design.pattern.factory;

/**
 * <a href="https://www.runoob.com/design-pattern/factory-pattern.html">工厂模式</a>
 * @author wangjiahao
 * @date 2023/8/4 13:50
 */
public class FactoryPatternDemo {
    public static void main(String[] args) {
        final ShapeFactory shapeFactory = new ShapeFactory();
        final Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

        final Shape rectangle = shapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        final Shape square = shapeFactory.getShape("SQUARE");
        square.draw();
        /**
         * Inside Circle::draw() method.
         * Inside Rectangle::draw() method.
         * Inside Square::draw() method.
         */
    }
}
