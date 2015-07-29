package com.jee.solr.query;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/7/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Shard {

    String[] shardNames() default {};
}
