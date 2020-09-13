package bus.serviceimpl;

import bus.Vo.SaleVo;
import bus.dao.CustomerDao;
import bus.dao.MessageDao;
import bus.dao.SaleDao;
import bus.domian.Customer;
import bus.domian.Message;
import bus.domian.Sale;
import bus.service.SaleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.utils.DataGridView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SaleServiceImpl implements SaleService {
    @Autowired
    private SaleDao saleDao;
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private MessageDao messageDao;

    /*添加一条销售记录单*/
    @Override
    public void addSale(SaleVo saleVo) {
      this.saleDao.insert(saleVo);
    }

    /*查询所有出售表记录单*/
    @Override
    public DataGridView queryAllSale(SaleVo saleVo) {
        Page<Object> page= PageHelper.startPage(saleVo.getPage(),saleVo.getLimit());
        List<Sale> data=this.saleDao.queryAllSale(saleVo);
        return new DataGridView(page.getTotal(),data);
    }

    /*	 修改出租单信息*/
    @Override
    public void updateSale(SaleVo saleVo) {
        this.saleDao.updateSale(saleVo);
    }

    /*去查出出售表单中的订购数量*/
    @Override
    public Sale queryOneNuber(SaleVo saleVo) {
        return this.saleDao.queryOneNuber(saleVo);
    }
    /*删除租单信息*/
    @Override
    public void deleteSale(String saleid) {
        this.saleDao.deleteSale(saleid);
    }

    /*根据出售单号查询出售单信息*/
    @Override
    public Sale queryOneById(String saleid) {
        return this.saleDao.queryOneById(saleid);
    }

    /**
     * 根据出售单号加载出售单的表单数据
     * @param rentid
     * @return
     */
    @Override
    public Map<String, Object> initSaleFormData(String saleid) {
        //查询出售单
        Sale sale=this.saleDao.queryOneById(saleid);
        //查询客户
        Customer customer=this.customerDao.queryCustomerOne(sale.getIdentity());
        //查询礼品信息
        Message message=this.messageDao.queryMessageByGiftNumber(sale.getGiftnumber());

        /*Object是接受类型   也就是value  key是String*/
        Map<String, Object> map=new HashMap<String, Object>();

        map.put("sale", sale);
        map.put("customer", customer);
        map.put("message", message);
        return map;
    }
}
