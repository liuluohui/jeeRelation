package com.jee.solr.entity;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.SolrDocument;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2015/7/25.
 */
@SolrDocument(solrCoreName = "db")
public class Product implements Serializable {

    @Id
    @Field
    private String user_id;

    @Field
    private String code;


    public Product() {
    }

    public Product(String user_id, String code) {
        this.user_id = user_id;
        this.code = code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
