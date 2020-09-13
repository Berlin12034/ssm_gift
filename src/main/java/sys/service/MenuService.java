package sys.service;

import org.apache.ibatis.annotations.Param;
import sys.Vo.MenuVo;
import sys.domian.Menu;
import sys.utils.DataGridView;

import java.util.List;

/*菜单管理的服务接口*/
public interface MenuService {

    /*查询所有菜单返回
    * List<Menu>
    超级管理员用*/
    /*左边的菜单栏*/
    public List<Menu> queryAllMenuForList(MenuVo menuVo);
    /*根据用户id查询用户的可用菜单*/
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);


    /*查询所有菜单*/
    DataGridView queryAlllMenu(MenuVo menuVo);
    /*添加菜单*/
    void addMenu(MenuVo menuVo);
   /*菜单修改*/
    void updateMenu(MenuVo menuVo);
   /**/
    Integer queryMenuByPid(Integer id);
    /*根据id删除菜单*/
    void deleteMenu(MenuVo menuVo);


}
