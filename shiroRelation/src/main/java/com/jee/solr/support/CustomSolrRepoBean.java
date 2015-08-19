package com.jee.solr.support;

import com.jee.solr.query.ShardParser;
import com.jee.solr.query.SimpleShardQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.core.support.TransactionalRepositoryFactoryBeanSupport;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.mapping.SimpleSolrMappingContext;
import org.springframework.data.solr.core.query.Query;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/7/29.
 */
public class CustomSolrRepoBean<T extends Repository<S, ID>, S, ID extends Serializable> extends TransactionalRepositoryFactoryBeanSupport<T, S, ID> {

    private SolrServer solrServer;
    private SolrOperations operations;
    private boolean schemaCreationSupport;
    private SimpleSolrMappingContext solrMappingContext;

    public CustomSolrRepoBean() {
    }

    public void setSolrOperations(SolrOperations operations) {
        this.operations = operations;
    }

    public void setSolrServer(SolrServer solrServer) {
        this.solrServer = solrServer;
    }

    public void setSchemaCreationSupport(boolean schemaCreationSupport) {
        this.schemaCreationSupport = schemaCreationSupport;
    }

    public void setSolrMappingContext(SimpleSolrMappingContext solrMappingContext) {
        this.solrMappingContext = solrMappingContext;
        super.setMappingContext(solrMappingContext);
    }

    public SimpleSolrMappingContext getSolrMappingContext() {
        return this.solrMappingContext;
    }

    protected SolrOperations getSolrOperations() {
        return this.operations;
    }

    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        Assert.isTrue(this.operations != null || this.solrServer != null, "SolrOperations or SolrServer must be configured!");
        if (operations != null && operations instanceof SolrTemplate) {
            ((SolrTemplate) operations).registerQueryParser(SimpleShardQuery.class, new ShardParser());
            ((SolrTemplate) operations).registerQueryParser(Query.class, new ShardParser());
        }
    }


    protected RepositoryFactorySupport doCreateRepositoryFactory() {
        CustomSolrRepositoryFactory factory = this.operations != null ? new CustomSolrRepositoryFactory(this.operations) : new CustomSolrRepositoryFactory(this.solrServer);
        factory.setSchemaCreationSupport(this.schemaCreationSupport);
        return factory;
    }
}
