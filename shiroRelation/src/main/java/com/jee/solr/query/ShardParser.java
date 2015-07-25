package com.jee.solr.query;

import com.sun.jmx.mbeanserver.NamedObject;
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

        if (query instanceof AbstractQueryDecorator) {
            try {
                Field field = AbstractQueryDecorator.class.getDeclaredField("query");
                field.setAccessible(true);
                fillShard(field.get(query), solrQuery);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return solrQuery;
    }

    private void fillShard(Object query, SolrQuery solrQuery) {
        if (query instanceof SimpleShardQuery) {
            solrQuery.setParam("shards.tolerant", true);
            // solrQuery.setParam("_router_", ((SimpleShardQuery) query).getShardNames().toString());
        }
    }
}
