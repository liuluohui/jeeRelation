package com.jee.solr.repositories;

import com.jee.solr.entity.Product;
import com.jee.solr.query.ShardParser;
import com.jee.solr.query.SimpleShardQuery;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrPageRequest;
import org.springframework.data.solr.repository.support.SimpleSolrRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by Administrator on 2015/7/25.
 */
@Service
public class ProductReposityImpl implements ProductReposity<Product, String> {


    @Autowired
    private SolrTemplate solrTemplate;

    @Override
    public Page<Product> queryProductByTitle(String title) {
        SimpleShardQuery query = new SimpleShardQuery();
        query.addCriteria(new Criteria("title").expression("aaa"));
        query.setPageRequest(new PageRequest(0, 10));
        return solrTemplate.queryForPage(query, Product.class);
    }
}
