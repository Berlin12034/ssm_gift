package bus.dao;

import bus.Vo.CustomerVo;
import bus.domian.Customer;

import java.util.List;

public interface CustomerDao {
    /*查询所有顾客*/
    List<Customer> queryAllCustomer(Customer customer);
    /*根据id删除客户*/
    void deleteCustomer(String identity);
    /*添加客户*/
    void insert(CustomerVo customerVo);
    /*客户修改*/
    void updateCustomer(CustomerVo customerVo);
    /*根据客户身份证号查询信息，用在判断这个客户是否存在，在出售表哪里*/
    Customer queryCustomerOne(String identity);
}
