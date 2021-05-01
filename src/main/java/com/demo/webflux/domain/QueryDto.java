package com.demo.webflux.domain;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

public class QueryDto {

    @PositiveOrZero
    private final Integer page;

    @Min(1)
    @Max(50)
    private final Integer perPage;

    private final String name;

    public QueryDto(Integer page, Integer perPage, String name) {
        this.page = page;
        this.perPage = perPage;
        this.name = name;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public String getName() {
        return name;
    }

    public Query toQuery() {
        var query = new Query();

        if (!isEmpty(this.getName())) {
            query.addCriteria(Criteria.where("name").is(this.getName()));
        }

        return query;
    }
}
