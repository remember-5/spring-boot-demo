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
package com.remember5.design.pattern.builder;

/**
 * @author wangjiahao
 * @date 2023/8/4 15:42
 */
public class BuilderPatternDemo {
    public static void main(String[] args) {
        final MealBuilder mealBuilder = new MealBuilder();
        final Meal vegMeal = mealBuilder.prepareVegMeal();
        System.err.println("Veg Meal");
        vegMeal.showItems();
        System.err.println("Total Cost:" + vegMeal.getCost());

        final Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        System.err.println("Non Veg Meal");
        nonVegMeal.showItems();
        System.err.println("Total Cost: " + nonVegMeal.getCost());


    }


}
