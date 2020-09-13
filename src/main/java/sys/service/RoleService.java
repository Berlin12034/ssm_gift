package sys.service;

import sys.Vo.MenuVo;
import sys.Vo.RoleVo;
import sys.domian.Menu;
import sys.domian.Role;
import sys.utils.DataGridView;

import java.util.List;

/*菜单管理的服务接口*/
public interface RoleService {

    /*查询所有菜单返回
* List<Role>
超级管理员用*/
    public List<Role> queryAllRoleForList(RoleVo roleVo);
    /*根据用户id查询用户的可用角色*/
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo,Integer userId);


    /*查询所有角色*/
    DataGridView queryAllRole(RoleVo roleVo);
    /*添加角色*/
    void addRole(RoleVo roleVo);
    /*指定id角色修改*/
    void updateRole(RoleVo roleVo);

    /*根据id删除角色*/
    void deleteRole(Integer roleid);

    /*批量删除角色*/
    void deleteBatchRole(Integer [] ids);

    /*加载角色管理分配菜单的json*/
    DataGridView initRoleMenuTreeJson(Integer roleid);
    /*保存角色，分配菜单拥有的权限*/
    void savaRoleMenu(RoleVo roleVo);
}
