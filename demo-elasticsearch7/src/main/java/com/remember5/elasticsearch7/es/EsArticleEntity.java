package com.remember5.elasticsearch7.es;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.*;
import java.sql.Date;

/**
 *
 * @author wangjiahao
 * @date 2022/5/25 19:42
 */
@Data
@Document(indexName = "article")
@Setting(shards = 3, replicas = 0)
public class EsArticleEntity {
    @Id
    private int id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String content;

    @Field(type = FieldType.Text)
    private String author;

    @Field(type = FieldType.Date)
    private Date createDate;

    @Field(type = FieldType.Date)
    private Date updateDate;

    @Field(type = FieldType.Boolean)
    private Boolean status;


}
