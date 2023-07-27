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
package com.remember.dynamic.datasource.mybatisplus.service;

/**
 * @author wangjiahao
 * @date 2023/7/27 15:27
 */
public interface ISqliteService {
    /**
     * 保存
     */
    void save();

    /**
     * 无事物的 两条保存sql， 第一条成功，第二条失败，互不影响
     */
    void twiceSave();
    /**
     * 有事物的 两条保存sql 需要两条都保存成功才可以
     */
    void tTwiceSave();

    /**
     * 调用内部方法，无回滚, 结果同twiceSave
     */
    void callInner() throws Exception;

    /**
     * 调用内部方法 tCallInnerError2，无回滚
     */
    void tCallInnerError() throws Exception;

    /**
     * 调用内部方法，有回滚
     */
    void tCallInnerError2() throws Exception;


    /**
     * 调用内部方法 tCallInnerError2，无回滚
     */
    void tCallInnerError3() throws Exception;

    /**
     * 调用内部方法，有回滚
     */
    void CallInnerError2() throws Exception;


    /**
     * 调用内部方法 回滚
     */
    void tCallInner() throws Exception;

    /**
     * 调用内部方法 回滚
     */
    void tCallInner2() throws Exception;

}
