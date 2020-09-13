package bus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*业务控制器*/
@Controller
@RequestMapping("/gift")
public class GiftController {
    /*跳转到客户管理的页面*/
    @RequestMapping("/toCustomerManager")
    public String toCustomerManager(){
        System.out.println("我到这里了");
        return "view/gift/customer/customerManager";
    }

    /*跳转到礼品管理的页面*/
    @RequestMapping("/toMessageManager")
    public String toMessageManager(){
        System.out.println("我到这里了");
        return "view/gift/message/messageManager";
    }

    /*跳转到礼品出售管理的页面*/
    @RequestMapping("/toSaleGiftManager")
    public String toSaleGiftManager(){
        System.out.println("我到这里了");
        return "view/gift/sale/saleGiftManager";
    }

    /*跳转到礼品出售管理的页面*/
    @RequestMapping("/toSaleManager")
    public String toSaleManager(){
        System.out.println("我到这里了");
        return "view/gift/sale/saleManager";
    }

    /*跳转到出售信息查询的页面*/
    @RequestMapping("/toQuerySaleManager")
    public String toQuerySaleManager(){
        System.out.println("我到这里了");
        return "view/gift/sale/querySaleManager";
    }
}
