package com.jee.solr.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;

import java.util.List;

/**
 * Created by Administrator on 2015/7/25.
 */
public class SimpleShardQuery extends SimpleQuery {

    private List<String> shardNames;


    public SimpleShardQuery() {
    }

    /**
     * @param criteria
     */
    public SimpleShardQuery(Criteria criteria) {
        super(criteria, null);
    }

    /**
     * @param queryString
     * @since 1.1
     */
    public SimpleShardQuery(String queryString) {
        super(new SimpleStringCriteria(queryString));
    }

    /**
     * @param criteria
     * @param pageable
     */
    public SimpleShardQuery(Criteria criteria, Pageable pageable) {
        super(criteria, pageable);
    }

    /**
     * @param queryString
     * @param pageable
     * @since 1.1
     */
    public SimpleShardQuery(String queryString, Pageable pageable) {
        this(new SimpleStringCriteria(queryString), pageable);
    }


    public List<String> getShardNames() {
        return shardNames;
    }

    public void setShardNames(List<String> shardNames) {
        this.shardNames = shardNames;
    }
}
