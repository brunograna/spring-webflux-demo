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
public class PageDto<T> {
    
    private Integer page;
    
    private Integer perPage;
    
    private Long totalPages;
    
    private Long total;
    
    private List<T> data;
    
    public PageDto(Integer page,
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
    
    public PageDto<T> setPage(Integer page) {
        this.page = page;
        return this;
    }
    
    public Integer getPerPage() {
        return perPage;
    }
    
    public PageDto<T> setPerPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }
    
    public Long getTotalPages() {
        return totalPages;
    }
    
    public PageDto<T> setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
        return this;
    }
    
    public Long getTotal() {
        return total;
    }
    
    public PageDto<T> setTotal(Long total) {
        this.total = total;
        return this;
    }
    
    public List<T> getData() {
        if (CollectionUtils.isEmpty(this.data)) {
            this.data = new ArrayList<>();
        }
        return data;
    }
    
    public PageDto<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
    
    @Override
    public String toString() {
        return "PageDto{" +
               "page=" + page +
               ", perPage=" + perPage +
               ", totalPages=" + totalPages +
               ", total=" + total +
               ", data=" + data +
               '}';
    }
}
