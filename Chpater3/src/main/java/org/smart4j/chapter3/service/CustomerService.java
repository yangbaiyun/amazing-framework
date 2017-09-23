package org.smart4j.chapter3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.amazing.framework.annotation.Service;
import org.smart4j.chapter3.model.Customer;


/**
 * 提供客户数据服务
 */
@Service
public class CustomerService {

    /**
     * 获取客户列表
     */
    public List<Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        List<Customer> list=new ArrayList<Customer>();
        Customer customer=new Customer();
        customer.setContact("chenghengliang");
        customer.setEmail("2333@qq.com");
        customer.setId(3333);
        customer.setName("程恒亮");
        customer.setTelephone("2343434");
        list.add(customer);
        return list;
    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        Customer customer=new Customer();
        customer.setContact("chenghengliang");
        customer.setEmail("2333@qq.com");
        customer.setId(3333);
        customer.setName("程恒亮");
        customer.setTelephone("2343434");
        return customer;
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return true;
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return true;
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return true;
    }
}
