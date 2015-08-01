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
    private String id;

    @Field
    private List<String> title;


    public Product() {
    }

    public Product(String id, List<String> title) {
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", title=" + title +
                '}';
    }
}
