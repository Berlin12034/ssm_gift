package sys.controller;
/*菜单管理的控制器*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.Vo.MenuVo;
import sys.constast.SysConstast;
import sys.domian.Menu;
import sys.domian.User;
import sys.service.MenuService;
import sys.utils.*;

import java.util.ArrayList;
import java.util.List;

@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @RequestMapping("/loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo) {
        /*得到当前登陆用户*/
        User user = (User) WebUtils.getHttpSession().getAttribute("user");
        List<Menu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        //超级管理员
        if (user.getType() == SysConstast.USER_TYPE_SUPER) {
            //根据传进来的menuVo去查询
            list = this.menuService.queryAllMenuForList(menuVo);
            System.out.println(list);
        } else {
            list = this.menuService.queryMenuByUserIdForList(menuVo, user.getUserid());
        }
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes,就是把所有menu在数据库中找出来
        //一个一个的遍历
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            /*记住这个错,spread在数据库不能为null,只有0和1，要不然判断就报空指针 */
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));

        }
        /*调用TreeNodeBuilder类的方法*/
        return TreeNodeBuilder.builder(nodes, 1);
    }

       /*加载菜单管理左边的菜单树*/
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<Menu> list=this.menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes=new ArrayList<>();
        //把list里面的数据放到nodes,就是把所有menu在数据库中找出来
        //一个一个的遍历
        for (Menu menu : list) {
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            /*记住这个错,spread在数据库不能为null,只有0和1，要不然判断就报空指针 */
            Boolean spread = menu.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id, pid, title, icon, href, spread, target));

        }
        /*把所有list数据存到一个对象里面*/
        return new DataGridView(nodes);
    }


    /*加载菜单列表返回DataGridView*/
     @RequestMapping("loadAllMenu")
     public DataGridView loadAllMenu(MenuVo menuVo) {
         return this.menuService.queryAlllMenu(menuVo);
     }

     /*添加菜单
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
       try{
           this.menuService.addMenu(menuVo);
           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }


    /*添加菜单
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try{
            this.menuService.updateMenu(menuVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }
    }
    /*根据id判断当前菜单有没有子节点
    * 有返回code>=0
    * 没有返回code<0*/
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        /*根据pid查询菜单数量*/
        Integer count=this.menuService.queryMenuByPid(menuVo.getId());
        if(count>0){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_FALSE;
        }
    }

    /*删除菜单*/
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
       try {
           this.menuService.deleteMenu(menuVo);
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}

