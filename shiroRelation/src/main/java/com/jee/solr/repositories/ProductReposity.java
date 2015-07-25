package com.jee.solr.repositories;

import org.springframework.data.domain.Page;

/**
 * Created by Administrator on 2015/7/25.
 */

public interface ProductReposity<Product, String> {

    Page<Product> queryProductByTitle(String title);

}
