package bus.controller;
/*客户管理的控制器*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import bus.Vo.CustomerVo;
import sys.domian.User;
import bus.service.CustomerService;
import sys.utils.DataGridView;
import sys.utils.ResultObj;
import sys.utils.WebUtils;

import java.util.Date;


@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    /*加载客户列表返回DataGridView*/
     @RequestMapping("/loadAllCustomer")
     public DataGridView loadAllCustomer(CustomerVo customerVo) {
         return this.customerService.queryAllCustomer(customerVo);
     }

     /*添加客户
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("/addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
       try{
           /*把系统时间弄进去*/
           customerVo.setCreatetime(new Date());
           this.customerService.addCustomer(customerVo);
           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }

    /*修改客户
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo){
        try{
            /*把系统时间弄进去*/
            User user= (User) WebUtils.getHttpSession().getAttribute("user");
            /*把系统时间弄进去*/
            customerVo.setCreatetime(new Date());
            this.customerService.updateCustomer(customerVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }


    }

    /*删除客户*/
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(CustomerVo customerVo){
       try {
           this.customerService.deleteCustomer(customerVo.getIdentity());
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除客户*/
    @RequestMapping("deleteBatchCustomer")
    public ResultObj deleteBatchCustomer(CustomerVo customerVo){
        try {
            this.customerService.deleteBatchCustomer(customerVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

