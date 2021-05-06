package com.demo.webflux.domain.interfaces;

import org.springframework.data.mongodb.core.query.Query;

public interface QueryData {

    Integer getPage();
    Integer getPerPage();
    String getName();

    Query toQuery();
}
