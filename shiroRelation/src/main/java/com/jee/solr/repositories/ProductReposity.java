package com.jee.solr.repositories;

import com.jee.solr.entity.Product;
import com.jee.solr.query.Shard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;


/**
 * Created by Administrator on 2015/7/25.
 */

public interface ProductReposity extends SolrCrudRepository<Product, String> {

    @Query(value = "title:?0")
    @Shard
    Page<Product> queryProductByTitle(String title, Pageable page);

}
