package bus.service;

import bus.Vo.CustomerVo;
import bus.domian.Customer;
import sys.utils.DataGridView;

import java.util.List;

public interface CustomerService {

    /*查询所有客户*/
    DataGridView queryAllCustomer(CustomerVo customerVo);
    /*添加客户*/
    void addCustomer(CustomerVo customerVo);

    /*修改客户*/
    void updateCustomer(CustomerVo customerVo);

    /*根据id删除客户*/
    void deleteCustomer(String identity);

    /*批量删除客户*/
    void deleteBatchCustomer(String[] identitys);

    /*根据客户身份证号查询信息，用在判断这个客户是否存在，在出售表哪里*/
    Customer queryCustomerByIdentity(String identity);
/*到处客户数据*/
    List<Customer> queryCustomerAllList(CustomerVo customerVo);
}
