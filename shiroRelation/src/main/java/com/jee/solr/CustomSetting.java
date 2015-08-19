package com.jee.solr;

import com.jee.solr.query.ShardParser;
import com.jee.solr.query.SimpleShardQuery;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Query;

/**
 * Created by Administrator on 2015/7/25.
 */
public class CustomSetting {

    private SolrTemplate template;

    public void registerParser() {
        template.registerQueryParser(SimpleShardQuery.class, new ShardParser());
        template.registerQueryParser(Query.class, new ShardParser());
    }


    public SolrTemplate getTemplate() {
        return template;
    }

    public void setTemplate(SolrTemplate template) {
        this.template = template;
    }
}
