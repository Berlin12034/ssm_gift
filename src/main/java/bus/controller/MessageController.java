package bus.controller;
/*礼品信息管理的控制器*/

import bus.Vo.MessageVo;
import bus.domian.Message;
import bus.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.constast.SysConstast;
import sys.domian.User;
import sys.utils.AppFileUtils;
import sys.utils.DataGridView;
import sys.utils.ResultObj;
import sys.utils.WebUtils;

import java.util.Date;
import java.util.List;


@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageService messageService;


    /*加载礼品信息列表返回DataGridView*/
     @RequestMapping("/loadAllMessage")
     public DataGridView loadAllMessage(MessageVo messageVo) {
         return this.messageService.queryAllMessage(messageVo);
     }

     /*添加礼品信息
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("/addMessage")
    public ResultObj addMessage(MessageVo messageVo){

       try{
           /*看看有没有相同的礼品*/
           List<Message> list=this.messageService.queryOnemessage(messageVo);
           if(list.size()==0) {
               /*把系统时间弄进去*/
               messageVo.setCreatetime(new Date());
               /*把临时图片_temp后缀去掉，因为它点保存了*/
               /*SysConstast.FILE_UPLOAD_TEMP是文件后缀*/
               if (!messageVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)) {
                   /*不等于默认图片就改名字*/
                   String filePath = AppFileUtils.updateFileName(messageVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                   messageVo.setCarimg(filePath);
               }
               this.messageService.addMessage(messageVo);

           }else {
               Integer  snumber = null;
               /*把系统时间弄进去*/
               messageVo.setCreatetime(new Date());
               for(Message message :list){
                 snumber= message.getS_number();
               }
               messageVo.setS_number(snumber+messageVo.getS_number());
               this.messageService.updateOneMessage(messageVo);
           }

           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }

    /*修改礼品信息
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/updateMessage")
    public ResultObj updateMessage(MessageVo messageVo) {
        try {
            /*进去查看根据礼品类型和礼品名称*/
            List<Message> list=this.messageService.queryOnemessage(messageVo);
            if(list.size()==0) {
                String carimg = messageVo.getCarimg();
                if (carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                    String filePath = AppFileUtils.updateFileName(messageVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                    messageVo.setCarimg(filePath);

                    //把原来的删除
                    Message message = this.messageService.queryMessageByGiftNumber(messageVo.getGiftnumber());
                    /*获取到原来图片的路径*/
                    //把原来的删除
                    AppFileUtils.removeFileByPath(message.getCarimg());
                }
                messageVo.setCreatetime(new Date());
                this.messageService.updateMessage(messageVo);
                return ResultObj.UPDATE_SUCCESS;

            }
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_CUNERROR;
        } catch (Exception e) {
            return ResultObj.UPDATE_ERROR;
        }


    }
    /*删除礼品信息*/
    @RequestMapping("deleteMessage")
    public ResultObj deleteMessage(MessageVo messageVo){
       try {
           this.messageService.deleteMessage(messageVo.getGiftnumber());
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除礼品信息*/
    @RequestMapping("deleteBatchMessage")
    public ResultObj deleteBatchMessage(MessageVo messageVo){
        try {
            this.messageService.deleteBatchMessage(messageVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}

