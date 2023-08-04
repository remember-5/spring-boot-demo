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
package com.remember5.design.pattern.abstractFactory;

/**
 * @author wangjiahao
 * @date 2023/8/4 15:05
 */
public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        final AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

        final Shape circle = shapeFactory.getShape("CIRCLE");
        circle.draw();

        final Shape square = shapeFactory.getShape("SQUARE");
        square.draw();

        final Shape rectangle = shapeFactory.getShape("RECTANGLE");
        rectangle.draw();

        final AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");

        final Color red = colorFactory.getColor("RED");
        red.fill();

        final Color green = colorFactory.getColor("GREEN");
        green.fill();

        final Color blue = colorFactory.getColor("BLUE");
        blue.fill();
        /*
            Inside Circle::draw() method.
            Inside Square::draw() method.
            Inside Rectangle::draw() method.
            Inside Red::fill() method.
            Inside Green::fill() method.
            Inside Blue::fill() method.
         */
    }
}
