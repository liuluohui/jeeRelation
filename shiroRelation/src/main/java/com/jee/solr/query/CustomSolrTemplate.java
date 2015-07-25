package com.jee.solr.query;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.solr.core.QueryParser;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.convert.SolrConverter;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.data.solr.server.SolrServerFactory;

/**
 * Created by Administrator on 2015/7/25.
 */
public class CustomSolrTemplate extends SolrTemplate {
    public CustomSolrTemplate(SolrServer solrServer) {
        super(solrServer);
    }

    public CustomSolrTemplate(SolrServer solrServer, String core) {
        super(solrServer, core);
    }

    public CustomSolrTemplate(SolrServerFactory solrServerFactory) {
        super(solrServerFactory);
    }

    public CustomSolrTemplate(SolrServerFactory solrServerFactory, SolrConverter solrConverter) {
        super(solrServerFactory, solrConverter);
    }

    public void registerQueryParser(Class clazz, QueryParser queryParser) {
        super.registerQueryParser(clazz, queryParser);
    }
}
