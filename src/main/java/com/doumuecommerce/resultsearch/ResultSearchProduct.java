package com.doumuecommerce.resultsearch;

import com.doumuecommerce.product.Product;

import java.io.Serializable;
import java.util.List;

public class ResultSearchProduct implements Serializable {
    private int pageNo;
    private int count;
    private List<Product> list;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Product> getList() {
        return list;
    }

    public void setList(List<Product> list) {
        this.list = list;
    }
}
