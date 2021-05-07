package com.demo.webflux.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"page", "per_page", "total_pages", "total", "data"})
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pagination<T> {
    
    private final Integer page;
    private final Integer perPage;
    private final Long totalPages;
    private final Long total;
    private final List<T> data;
    
    public Pagination(Integer page,
                      Integer perPage,
                      Long totalPages,
                      Long total,
                      List<T> data) {
        this.page = page;
        this.perPage = perPage;
        this.totalPages = totalPages;
        this.total = total;
        this.data = data;
    }
    
    public Integer getPage() {
        return page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getData() {
        if (CollectionUtils.isEmpty(this.data)) {
            return new ArrayList<>();
        }
        return data;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", perPage=" + perPage +
                ", totalPages=" + totalPages +
                ", total=" + total +
                ", data=" + data +
                '}';
    }
}
