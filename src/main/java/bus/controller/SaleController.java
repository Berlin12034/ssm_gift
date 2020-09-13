package bus.controller;

import java.util.Date;
import java.util.Map;

import bus.domian.Message;
import bus.domian.Sale;
import bus.service.MessageService;
import bus.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bus.domian.Customer;
import bus.service.CustomerService;
import bus.service.SaleService;
import bus.Vo.SaleVo;
import sys.constast.SysConstast;
import sys.domian.User;
import sys.utils.DataGridView;
import sys.utils.RandomUtils;
import sys.utils.ResultObj;
import sys.utils.WebUtils;

/**
 * 礼品销售管理的控制器
 * @author LJH
 *
 */
@RestController
@RequestMapping("sale")
public class SaleController {

	@Autowired
	private SaleService saleService;
	
	@Autowired
	private CustomerService customerService;
    @Autowired
    private MessageService messageService;

	/**
	 * 检查身份证号是否存在
	 */
	@RequestMapping("checkCustomerExist")
	public ResultObj checkCustomerExist(SaleVo saleVo) {
		Customer customer=customerService.queryCustomerByIdentity(saleVo.getIdentity());
		if(null!=customer) {
			return ResultObj.STATUS_TRUE;
		}else {
			return ResultObj.STATUS_FALSE;
		}
	}
	
	/**
	 * 初始化添加礼品出售表单数据
	 */
	@RequestMapping("initSaleFrom")
	public SaleVo initSaleFrom(SaleVo saleVo) {
		//生成出售单号
		saleVo.setSaleid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
		//设置
	/*	saleVo.setBegindate(new Date());*/
        /*为了得到客户真实名字*/
      Customer customer= this.customerService.queryCustomerByIdentity(saleVo.getIdentity());
        saleVo.setCustname(customer.getCustname());
		User user=(User) WebUtils.getHttpSession().getAttribute("user");
		//设置操作员
		saleVo.setOpername(user.getName());
		return saleVo;
	}
	
	/**
	 * 保存出租单信息
	 */
	@RequestMapping("saveSale")
	public ResultObj saveSale(SaleVo saleVo) {
		try {
			//设置创建时间
			/*saleVo.setCreatetime(new Date());*/
            /*去把库存数量查出来*/
         Message message =this.messageService.queryMessageByGiftNumber(saleVo.getGiftnumber());
            if(message.getS_number()-saleVo.getNumber()>=0){
                /*下单库存数量相减*/
                message.setS_number(message.getS_number()-saleVo.getNumber());
                this.messageService.updateMessageNumber(message);
			saleVo.setT_price(saleVo.getPrice()*saleVo.getNumber());
			//保存
			this.saleService.addSale(saleVo);
			return ResultObj.ADD_SUCCESS;
            }else {
            return ResultObj.ADD_ERROR1;
            }

		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.ADD_ERROR;
		}
	}
	
	
/*	***************出租单管理******************/
	/*加载所有出售表单*/

	@RequestMapping("loadAllSale")
	public DataGridView loadAllSale(SaleVo saleVo) {
		return this.saleService.queryAllSale(saleVo);
	}
	
	

/*	 修改出租单信息*/

	@RequestMapping("updateSale")
	public ResultObj updateSale(SaleVo saleVo) {
		try {
            /*去把库存数量查出来*/
            Message message =this.messageService.queryMessageByGiftNumber(saleVo.getGiftnumber());
            /*去查出出售表单中的订购数量*/
            Sale sale= this.saleService.queryOneNuber(saleVo);
            if(saleVo.getNumber()>sale.getNumber()&&message.getS_number()-(saleVo.getNumber()-sale.getNumber())>=0){
                /*下单库存数量相减*/
                message.setS_number(message.getS_number()-(saleVo.getNumber()-sale.getNumber()));
                this.messageService.updateMessageNumber(message);
             /*   saleVo.setT_price(saleVo.getPrice()*saleVo.getNumber());*/
                //保存
                this.saleService.updateSale(saleVo);
                return ResultObj.UPDATE_SUCCESS;
            }else if(saleVo.getNumber()<=sale.getNumber()){
                /*得到填写比原来下单小的数量，相减*/
            Integer snumber =sale.getNumber()-saleVo.getNumber();
            /*在现在的库存数量上加回*/
                message.setS_number(message.getS_number()+snumber);
                this.messageService.updateMessageNumber(message);
                this.saleService.updateSale(saleVo);
                return ResultObj.UPDATE_SUCCESS;

            }else {
                return ResultObj.UPDATE_ERROR1;
            }

		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.UPDATE_ERROR;
		}
	}



 /*删除租单信息*/

	@RequestMapping("deleteSale")
	public ResultObj deleteSale(SaleVo saleVo) {
		try {
			this.saleService.deleteSale(saleVo.getSaleid());
			return ResultObj.DELETE_SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultObj.DELETE_ERROR;
		}
	}

    /***
     * 根据出售单号查询出售单信息
     */
    @RequestMapping("querySaleExist")
    public Sale checkRentExist(String saleid) {
        /*不存在返回空*/
        Sale sale=saleService.queryOneById(saleid);//null   返回对象
        return sale;
    }


    /**
     * 根据出租单号加载检查单的表单数据
     */
    @RequestMapping("initSaleFormData")
    public Map<String,Object> initCheckFormData(String saleid){
        return this.saleService.initSaleFormData(saleid);
    }


}
