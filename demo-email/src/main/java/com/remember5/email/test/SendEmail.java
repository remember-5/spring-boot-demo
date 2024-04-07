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
package com.remember5.email.test;

import cn.hutool.extra.mail.Mail;
import cn.hutool.extra.mail.MailAccount;
import com.sun.mail.util.MailSSLSocketFactory;

import java.io.File;
import java.security.GeneralSecurityException;

/**
 * @author wangjiahao
 * @date 2024/4/6 15:35
 */
public class SendEmail {
    public static void main(String[] args) throws GeneralSecurityException {
        MailAccount account = new MailAccount();
        account.setHost("smtp.163.com");
        account.setPort(465);
        account.setAuth(true);
        account.setSslEnable(true);
        account.setFrom("test@163.com");
        account.setUser("test");
        account.setPass("");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        account.setCustomProperty("mail.smtp.ssl.socketFactory", sf);
        account.setCustomProperty("mail.smtp.proxy.host", "127.0.0.1");
        account.setCustomProperty("mail.smtp.proxy.port", "29999");


        final File file = new File("/Users/wangjiahao/Downloads/t_chats.sql");

        Mail.create(account)
                .setTos("1332661444@qq.com")
                .setTitle("邮箱验证")
                .setContent("您的验证码是：<h3>2333</h3>")
                .setHtml(true)
                .setFiles(file)
                .send();

    }
}
