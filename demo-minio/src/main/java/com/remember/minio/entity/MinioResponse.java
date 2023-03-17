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
package com.remember.minio.entity;

import io.minio.ObjectWriteResponse;
import okhttp3.Headers;

import java.io.Serializable;

/**
 * minio 上传返回对象
 *
 * @author wangjiahao
 * @date 2022/10/18 11:01
 */
public class MinioResponse extends ObjectWriteResponse implements Serializable {


    /**
     * 上传状态
     * true为成功 false为失败
     */
    private Boolean status;
    /**
     * 上传成功后的url
     */
    private String url;

    public Boolean stats() {
        return this.status;
    }

    public String url() {
        return this.url;
    }

    public MinioResponse(Headers headers, String bucket, String region, String object, String etag, String versionId, Boolean status, String url) {
        super(headers, bucket, region, object, etag, versionId);
        this.status = status;
        this.url = url;
    }

    public MinioResponse(ObjectWriteResponse o, Boolean stats, String url) {
        super(o.headers(), o.region(), o.object(), o.etag(), o.etag(), o.versionId());
        this.status = stats;
        this.url = url;
    }

}
