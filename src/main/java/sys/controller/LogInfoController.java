package sys.controller;
/*日志管理的控制器*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.Vo.LogInfoVo;
import sys.service.LogInfoService;
import sys.utils.DataGridView;
import sys.utils.ResultObj;

import javax.servlet.http.HttpServletRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/logInfo")
public class LogInfoController {

    @Autowired
    private LogInfoService logInfoService;


    /*加载日志列表返回DataGridView*/
     @RequestMapping("/loadAllLogInfo")
     public DataGridView loadAllLogInfo(LogInfoVo logInfoVo) {
         return this.logInfoService.queryAllLogInfo(logInfoVo);
     }

     /*添加日志
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("/addLogInfo")
    public ResultObj addLogInfo(LogInfoVo logInfoVo){
       try{
           this.logInfoService.addLogInfo(logInfoVo);
           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }


    /*删除日志*/
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(LogInfoVo logInfoVo){
       try {
           this.logInfoService.deleteLogInfo(logInfoVo.getId());
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除日志*/
    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo){
        try {
            this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

