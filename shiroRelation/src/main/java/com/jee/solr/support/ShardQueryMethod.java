package com.jee.solr.support;

import com.jee.solr.query.Shard;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.query.SolrEntityInformationCreator;
import org.springframework.data.solr.repository.query.SolrQueryMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by Administrator on 2015/7/28.
 */
public class ShardQueryMethod extends SolrQueryMethod {

    private Method method;

    public ShardQueryMethod(Method method, RepositoryMetadata metadata, SolrEntityInformationCreator solrInformationCreator) {
        super(method, metadata, solrInformationCreator);
        this.method = method;
    }

    public boolean isShardQuery() {
        return getShard() != null;
    }

    public String getShardNames() {
        Shard shard = getShard();
        if (shard != null) {
            String[] names = shard.shardNames();
            if (names != null && names.length > 0) {
                return Arrays.toString(names).replaceAll("\\[|\\]", "");
            }
        }
        return "";
    }

    private Shard getShard() {
        return this.method.getAnnotation(Shard.class);
    }


    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
