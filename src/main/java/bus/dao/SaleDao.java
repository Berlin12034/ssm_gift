package bus.dao;

import bus.Vo.SaleVo;
import bus.domian.Sale;

import java.util.List;

public interface SaleDao {
    /*添加一条礼品c出售信息信息*/
    void insert(SaleVo saleVo);

    /*查询*/
    public List<Sale> queryAllSale(Sale sale);

    /*	 修改出租单信息*/
    void updateSale(SaleVo saleVo);
    /*去查出出售表单中的订购数量*/
    Sale queryOneNuber(SaleVo saleVo);
    /*删除租单信息*/
    void deleteSale(String saleid);
    /*根据出售单号查询出售单信息*/
    Sale queryOneById(String saleid);
}
