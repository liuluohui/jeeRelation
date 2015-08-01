package com.jee.solr.repositories;

import com.jee.solr.entity.Product;
import com.jee.solr.query.SimpleShardQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.stereotype.Service;

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
        query.addCriteria(new Criteria("title").expression(title));
        query.setPageRequest(new PageRequest(0, 1000));
        return solrTemplate.queryForPage(query, Product.class);
    }

    @Override
    public void save(Product product) {
        solrTemplate.saveBean(product);
        solrTemplate.commit();
    }
}
