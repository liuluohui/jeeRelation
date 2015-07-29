package com.jee.solr.query;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.data.solr.core.DefaultQueryParser;
import org.springframework.data.solr.core.query.AbstractQueryDecorator;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SolrDataQuery;

import java.lang.reflect.Field;

/**
 * Created by Administrator on 2015/7/25.
 */
public class ShardParser extends DefaultQueryParser {


    @Override
    public SolrQuery constructSolrQuery(SolrDataQuery query) {
        SolrQuery solrQuery = super.constructSolrQuery(query);
        fillShard(query, solrQuery);

        return solrQuery;
    }

    private void fillShard(SolrDataQuery query, SolrQuery solrQuery) {
        if (!addShardInfo(query, solrQuery)) {
            if (query instanceof AbstractQueryDecorator) {
                Field field = null;
                try {
                    field = AbstractQueryDecorator.class.getDeclaredField("query");
                    field.setAccessible(true);
                    Query insQuery = (Query) field.get(query);
                    addShardInfo(insQuery, solrQuery);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean addShardInfo(SolrDataQuery query, SolrQuery solrQuery) {
        if (query instanceof SimpleShardQuery) {
            solrQuery.setParam("shards.tolerant", true);
            String shardNames = ((SimpleShardQuery) query).getShardNames();
            if (StringUtils.isNoneBlank(shardNames)) {
                solrQuery.setParam("_router_", shardNames);
            }
            return true;
        }
        return false;
    }
}
