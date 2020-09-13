package sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.Vo.UserVo;
import sys.service.UserService;
import sys.utils.DataGridView;
import sys.utils.ResultObj;

import java.util.List;

/*用户管理控制器*/
/*一定加这个RestController*/
/*因为加Controller返回的数据不帮转成json,而layui页面的数据请求后返回接收的必须是json，而RestController
* 返回的数据就帮转成json*/
@RestController
@RequestMapping("/user")
public class User1controller {

    @Autowired
    private UserService userService;


    /*加载用户列表返回DataGridView*/
    @RequestMapping("/loadAllUser")
    public DataGridView loadAllUser(UserVo userVo) {

        return this.userService.queryAllUser(userVo);
    }

    /*添加用户
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/addUser")
    public ResultObj addUser(UserVo userVo){
        try{
            this.userService.addUser(userVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e){
            return ResultObj.ADD_ERROR;
        }


    }


    /*添加用户
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/updateUser")
    public ResultObj updateUser(UserVo userVo){
        try{
            this.userService.updateUser(userVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }


    /*删除用户*/
    @RequestMapping("deleteUser")
    public ResultObj deleteUser(UserVo userVo){
        try {
            this.userService.deleteUser(userVo.getUserid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除用户*/
    @RequestMapping("deleteBatchUser")
    public ResultObj deleteBatchUser(UserVo userVo){
        try {
            this.userService.deleteBatchUser(userVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*重置用户密码*/
    @RequestMapping("resetUserPwd")
    public ResultObj resetUserPwd(UserVo userVo){
        try {
            this.userService.resetUserPwd(userVo.getUserid());
            return ResultObj.RESET_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.RESET_ERROR;
        }
    }

    /*加载用户管理的分配角色的数据*/
    @RequestMapping("initUserRole")
    public DataGridView initUserRole(UserVo userVo){

        return this.userService.queryUserRole(userVo.getUserid());
    }

    /*保存用户和角色的关系*/
    @RequestMapping("saveUserRole")
    public ResultObj saveUserRole(UserVo userVo){
        try {
            this.userService.savaUserRole(userVo);
         return ResultObj.DISPATCH_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
    }
}
