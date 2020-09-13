package sys.dao;

import org.apache.ibatis.annotations.Param;
import sys.Vo.MenuVo;
import sys.domian.Menu;

import java.util.List;

public interface MenuDao {
    /*插入一条菜单栏数据*/
    int insert(Menu menu);
    /*查询所有菜单*/
    List<Menu> queryAllMenu(Menu menu);
   /*更新方法*/
    void updateAllMenu(MenuVo menuVo);
    /*根据pid查询菜单数量*/
    Integer queryMenuByPid(Integer pid);
   /*删除菜单表的数据*/
    void deleteMenu(Integer id);

    /*根据菜单id删除sys_role_menu里面的数据*/
    /*@Param是MyBatis所提供的(org.apache.ibatis.annotations.Param)
    作为Dao层的注解，作用是用于传递参数，从而可以与SQL中的的字段名相对应
    这样service的值就给了这个叫mid了可以对应数据库字段叫mid的赋值*/
    void deleteRoleMenuByMid(@Param("mid")Integer mid);

   /*根据我们角色id查询菜单*/
    List<Menu> queryMenuByRoleId(@Param("available") Integer available, @Param("rid") Integer roleid);

    /*根据用户id查询菜单*/
    List<Menu> queryMenuByUid(@Param("available") Integer available,@Param("uid") Integer userId);
}
