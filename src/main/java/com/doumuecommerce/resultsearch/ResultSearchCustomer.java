package com.doumuecommerce.resultsearch;


import com.doumuecommerce.customer.Customer;

import java.io.Serializable;
import java.util.List;

public class ResultSearchCustomer implements Serializable {
    private int pageNo;
    private int count;
    private List<Customer> list;

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

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }
}
