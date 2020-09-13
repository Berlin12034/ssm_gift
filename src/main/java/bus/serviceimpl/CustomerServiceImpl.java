package bus.serviceimpl;

import bus.service.CustomerService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bus.Vo.CustomerVo;
import bus.dao.CustomerDao;
import bus.domian.Customer;

import sys.utils.DataGridView;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerDao customerDao;

    /*查询所有客户*/
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        /*传入这两个参数，page会帮我们处理
         * 页数
         * 每页大小*/
        Page<Object> page= PageHelper.startPage(customerVo.getPage(),customerVo.getLimit());
        List<Customer> data=this.customerDao.queryAllCustomer(customerVo);
        /*从后台得到总数和所有客户的数据*/
        return new DataGridView(page.getTotal(),data);
    }

    /*添加客户*/
    @Override
    public void addCustomer(CustomerVo CustomerVo) {
        this.customerDao.insert(CustomerVo);
    }

    /*客户修改*/
    @Override
    public void updateCustomer(CustomerVo customerVo) {
        this.customerDao.updateCustomer(customerVo);
    }

    /*根据id删除客户*/
    @Override
    public void deleteCustomer(String identity) {
           this.customerDao.deleteCustomer(identity);
    }

    /*批量删除*/
    @Override
    public void deleteBatchCustomer(String[] identitys) {
        for (String identity:identitys){
            this.deleteCustomer(identity);
        }

    }

    /*根据客户身份证号查询信息，用在判断这个客户是否存在，在出售表哪里*/
    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return this.customerDao.queryCustomerOne(identity);
    }

    /*到处客户数据*/
    @Override
    public List<Customer> queryCustomerAllList(CustomerVo customerVo) {
        return this.customerDao.queryAllCustomer(customerVo);
    }


}
