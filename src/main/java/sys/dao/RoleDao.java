package sys.dao;

import org.apache.ibatis.annotations.Param;
import sys.Vo.MenuVo;
import sys.Vo.RoleVo;
import sys.domian.Menu;
import sys.domian.Role;

import java.util.List;

public interface RoleDao {
    int deleteByPrimaryKey(Integer roleid);


    int insertSelective(Role record);

    int updateByPrimaryKey(Role record);

    /*插入一条菜单栏数据*/
    int insert(Role role);

    /*更新指定id角色方法*/
    void updateOneRole(RoleVo roleVo);
    /*根据pid查询菜单数量*/
    Integer queryMenuByPid(Integer pid);
    /*删除菜单表的数据*/
    void deleteRole(Integer id);

    //根据角色id删除sys_role_menu里面rid的数据，因为对应的数据也要删除掉
    /*@Param是MyBatis所提供的(org.apache.ibatis.annotations.Param)
    作为Dao层的注解，作用是用于传递参数，从而可以与SQL中的的字段名相对应
    这样service的值就给了这个叫mid了可以对应数据库字段叫mid的赋值*/
    void deleteRoleMenuByRid(@Param("mid")Integer mid);

    /*根据角色id删除sys_role_user里面的数据*/
    void deleteRoleUserByRid(Integer roleid);

    /*根据用户id删除sys_role_user里面的数据*/
    void deleteRoleUserByUid(Integer userid);

    /*查询角色*/
    List<Role> queryAllRole(Role role);

    /*在菜单表那边的方法调用的*/
    /*查询所有角色*/
    List<Role> queryAllMenu(RoleVo roleVo);

    /*保存角色，分配菜单拥有的权限*/
    /*sys_role_menu*/
    void insertRoleMenu(@Param("rid") Integer rid, @Param("mid") Integer mid);

    /*根据用户id查询拥有的角色*/
    List<Role> queryRoleByUid(@Param("available")Integer available,@Param("uid")Integer userid);

}
