package org.springframework.data.solr.repository.query;

import com.jee.solr.query.SimpleShardQuery;
import com.jee.solr.support.ShardQueryMethod;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.Field;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/7/28.
 */
public class ShardBaseQuery extends StringBasedSolrQuery {

    private static final Pattern PARAMETER_PLACEHOLDER = Pattern.compile("\\?(\\d+)");

    private SolrQueryMethod method;

    private String queryString;

    public ShardBaseQuery(SolrQueryMethod method, SolrOperations solrOperations) {
        this(method.getAnnotatedQuery(), method, solrOperations);
        this.method = method;
    }

    public ShardBaseQuery(String query, SolrQueryMethod queryMethod, SolrOperations solrOperations) {
        super(query, queryMethod, solrOperations);
        this.method = queryMethod;
        this.queryString = query;
    }

    @Override
    protected Query createQuery(SolrParameterAccessor parameterAccessor) {
        SimpleQuery simpleQuery = super.createQueryFromString(queryString, parameterAccessor);
        SimpleShardQuery shardQuery = copyProperties(simpleQuery);

        if (method instanceof ShardQueryMethod) {
            shardQuery.setShardNames(((ShardQueryMethod) method).getShardNames());
        }
        return shardQuery;
    }

    private SimpleShardQuery copyProperties(SimpleQuery simpleQuery) {
        SimpleShardQuery shardQuery = new SimpleShardQuery(simpleQuery.getCriteria());
        if (CollectionUtils.isNotEmpty(simpleQuery.getProjectionOnFields())) {
            shardQuery.addProjectionOnFields(simpleQuery.getProjectionOnFields().toArray(new Field[simpleQuery.getProjectionOnFields().size()]));
        }
        if (CollectionUtils.isNotEmpty(simpleQuery.getFilterQueries())) {
            for (FilterQuery filterQuery : simpleQuery.getFilterQueries()) {
                shardQuery.addFilterQuery(filterQuery);
            }
        }
        shardQuery.setOffset(simpleQuery.getOffset());
        shardQuery.setRows(simpleQuery.getRows());
        shardQuery.addSort(simpleQuery.getSort());
        shardQuery.setDefaultOperator(simpleQuery.getDefaultOperator());
        shardQuery.setTimeAllowed(simpleQuery.getTimeAllowed());
        shardQuery.setDefType(simpleQuery.getDefType());
        shardQuery.setGroupOptions(simpleQuery.getGroupOptions());
        shardQuery.setStatsOptions(simpleQuery.getStatsOptions());
        shardQuery.setJoin(simpleQuery.getJoin());
        shardQuery.setRequestHandler(simpleQuery.getRequestHandler());
        return shardQuery;
    }
}
