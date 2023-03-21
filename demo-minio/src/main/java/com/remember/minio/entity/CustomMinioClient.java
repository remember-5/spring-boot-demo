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

import com.google.common.collect.Multimap;
import io.minio.MinioClient;
import io.minio.errors.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutionException;

/**
 * @author wangjiahao
 * @date 2023/3/17 17:54
 */
public class CustomMinioClient extends MinioClient {
    protected CustomMinioClient(MinioClient client) {
        super(client);
    }

    //初始化分块上传任务
    public String initMultiPartUpload(String bucket, String region, String object, Multimap<String, String> headers, Multimap<String, String> extraQueryParams) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, ServerException, InternalException, XmlParserException, InvalidResponseException, ErrorResponseException, ExecutionException, InterruptedException, InvalidKeyException, InternalException {
//        CompletableFuture<CreateMultipartUploadResponse> response = this.createMultipartUpload(bucket, region, object, headers, extraQueryParams);
//        return response.get().result().uploadId();
        return null;
    }
}
