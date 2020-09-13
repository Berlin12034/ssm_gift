package bus.service;

import bus.Vo.SaleVo;
import bus.domian.Sale;
import sys.utils.DataGridView;

import java.util.Map;

public interface SaleService {
    /*添加一条销售记录单*/
    void addSale(SaleVo saleVo);

    /*查询所有出售表记录单*/
    DataGridView queryAllSale(SaleVo saleVo);
    /*	 修改出租单信息*/
    void updateSale(SaleVo saleVo);
/*去查出出售表单中的订购数量*/
    Sale queryOneNuber(SaleVo saleVo);
    /*删除租单信息*/
    void deleteSale(String saleid);
  /*  根据出售单号查询出售单信息*/
    Sale queryOneById(String saleid);

    /**
     * 根据出售单号加载出售单的表单数据
     * @param rentid
     * @return
     */
    Map<String, Object> initSaleFormData(String saleid);

}
