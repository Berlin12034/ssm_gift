package sys.controller;
/*角色管理的控制器*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.Vo.RoleVo;
import sys.service.RoleService;
import sys.utils.*;


@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    /*加载角色列表返回DataGridView*/
     @RequestMapping("/loadAllRole")
     public DataGridView loadAllRole(RoleVo roleVo) {
         return this.roleService.queryAllRole(roleVo);
     }

     /*添加角色
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("/addRole")
    public ResultObj addRole(RoleVo roleVo){
       try{
           this.roleService.addRole(roleVo);
           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }


    /*添加角色
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/updateRole")
    public ResultObj updateRole(RoleVo roleVo){
        try{
            this.roleService.updateRole(roleVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }


    /*删除角色*/
    @RequestMapping("deleteRole")
    public ResultObj deleteRole(RoleVo roleVo){
       try {
           this.roleService.deleteRole(roleVo.getRoleid());
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除角色*/
    @RequestMapping("deleteBatchRole")
    public ResultObj deleteBatchRole(RoleVo roleVo){
        try {
            this.roleService.deleteBatchRole(roleVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }


    /*加载角色管理分配菜单的json*/
    @RequestMapping("initRoleMenuTreeJson")
    public DataGridView initRoleMenuTreeJson(Integer roleid){
        return this.roleService.initRoleMenuTreeJson(roleid);
    }

    /*保存角色，分配菜单拥有的权限*/
    @RequestMapping("saveRoleMenu")
    public ResultObj saveRoleMenu(RoleVo roleVo){

        try{
            this.roleService.savaRoleMenu(roleVo);
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DISPATCH_ERROR;
        }
        return ResultObj.DISPATCH_SUCCESS;
    }
}

