package com.jee.solr.support;

import org.apache.solr.client.solrj.SolrServer;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.querydsl.QueryDslUtils;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.schema.SolrPersistentEntitySchemaCreator;
import org.springframework.data.solr.repository.query.*;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.data.solr.repository.support.SolrEntityInformationCreatorImpl;
import org.springframework.data.solr.server.SolrServerFactory;
import org.springframework.data.solr.server.support.MulticoreSolrServerFactory;
import org.springframework.data.solr.server.support.SolrServerUtils;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by Administrator on 2015/7/28.
 */
public class CustomSolrRepositoryFactory extends RepositoryFactorySupport {
    private SolrOperations solrOperations;
    private final SolrEntityInformationCreator entityInformationCreator;
    private SolrServerFactory factory;
    private SolrTemplateHolder templateHolder = new SolrTemplateHolder();
    private boolean schemaCreationSupport;

    public CustomSolrRepositoryFactory(SolrOperations solrOperations) {
        Assert.notNull(solrOperations);
        if (solrOperations instanceof SolrTemplate) {
            this.addSchemaCreationFeaturesIfEnabled((SolrTemplate) solrOperations);
        }

        this.solrOperations = solrOperations;
        this.entityInformationCreator = new SolrEntityInformationCreatorImpl(solrOperations.getConverter().getMappingContext());
    }

    public CustomSolrRepositoryFactory(SolrServer solrServer) {
        Assert.notNull(solrServer);
        this.solrOperations = this.createTemplate(solrServer);
        this.factory = new MulticoreSolrServerFactory(solrServer);
        this.entityInformationCreator = new SolrEntityInformationCreatorImpl(this.solrOperations.getConverter().getMappingContext());
    }

    private SolrTemplate createTemplate(SolrServer solrServer) {
        SolrTemplate template = new SolrTemplate(solrServer);
        this.addSchemaCreationFeaturesIfEnabled(template);
        template.afterPropertiesSet();
        return template;
    }

    public <T, ID extends Serializable> SolrEntityInformation<T, ID> getEntityInformation(Class<T> domainClass) {
        return this.entityInformationCreator.getEntityInformation(domainClass);
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {
        Object operations = this.solrOperations;
        if (this.factory != null) {
            SolrTemplate repository = new SolrTemplate(this.factory);
            if (this.solrOperations.getConverter() != null) {
                repository.setMappingContext(this.solrOperations.getConverter().getMappingContext());
            }

            repository.setSolrCore(SolrServerUtils.resolveSolrCoreName(metadata.getDomainType()));
            this.addSchemaCreationFeaturesIfEnabled(repository);
            repository.afterPropertiesSet();
            operations = repository;
        }

        SimpleSolrRepository repository1 = new SimpleSolrRepository(this.getEntityInformation(metadata.getDomainType()), (SolrOperations) operations);
        repository1.setEntityClass(metadata.getDomainType());
        this.templateHolder.add(metadata.getDomainType(), (SolrOperations) operations);
        return repository1;
    }

    private void addSchemaCreationFeaturesIfEnabled(SolrTemplate template) {
        if (this.isSchemaCreationSupport()) {
            template.setSchemaCreationFeatures(Collections.singletonList(SolrPersistentEntitySchemaCreator.Feature.CREATE_MISSING_FIELDS));
        }

    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isQueryDslRepository(metadata.getRepositoryInterface())) {
            throw new IllegalArgumentException("QueryDsl Support has not been implemented yet.");
        } else {
            return SimpleSolrRepository.class;
        }
    }

    private static boolean isQueryDslRepository(Class<?> repositoryInterface) {
        return QueryDslUtils.QUERY_DSL_PRESENT && QueryDslPredicateExecutor.class.isAssignableFrom(repositoryInterface);
    }

    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key) {
        return new CustomSolrRepositoryFactory.SolrQueryLookupStrategy();
    }

    public boolean isSchemaCreationSupport() {
        return this.schemaCreationSupport;
    }

    public void setSchemaCreationSupport(boolean schemaCreationSupport) {
        this.schemaCreationSupport = schemaCreationSupport;
    }

    private static class SolrTemplateHolder {
        private Map<Class<?>, SolrOperations> operationsMap;

        private SolrTemplateHolder() {
            this.operationsMap = new WeakHashMap();
        }

        void add(Class<?> domainType, SolrOperations repository) {
            this.operationsMap.put(domainType, repository);
        }

        SolrOperations getSolrOperations(Class<?> type) {
            return (SolrOperations) this.operationsMap.get(type);
        }
    }

    private class SolrQueryLookupStrategy implements QueryLookupStrategy {
        private SolrQueryLookupStrategy() {
        }

        public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, NamedQueries namedQueries) {
            SolrQueryMethod queryMethod = new ShardQueryMethod(method, metadata, entityInformationCreator);
            String namedQueryName = queryMethod.getNamedQueryName();
            SolrOperations solrOperations = this.selectSolrOperations(metadata);
            if (namedQueries.hasQuery(namedQueryName)) {
                String namedQuery = namedQueries.getQuery(namedQueryName);
                return new ShardBaseQuery(namedQuery, queryMethod, solrOperations);
            } else {
                return queryMethod.hasAnnotatedQuery() ? new ShardBaseQuery(queryMethod, solrOperations) : new PartTreeSolrQuery(queryMethod, solrOperations);
            }
        }

        private SolrOperations selectSolrOperations(RepositoryMetadata metadata) {
            SolrOperations ops = templateHolder.getSolrOperations(metadata.getDomainType());
            if (ops == null) {
                ops = solrOperations;
            }

            return ops;
        }
    }


}
