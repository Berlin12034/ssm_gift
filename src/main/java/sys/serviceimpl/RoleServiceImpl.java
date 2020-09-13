package sys.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.Vo.RoleVo;
import sys.constast.SysConstast;
import sys.dao.MenuDao;
import sys.dao.RoleDao;
import sys.domian.Menu;
import sys.domian.Role;
import sys.service.RoleService;
import sys.utils.DataGridView;
import sys.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
     @Autowired
     private RoleDao roleDao;
    @Autowired
     private MenuDao menuDao;
    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return null;
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return null;
    }

    /*查询所有角色*/
    public DataGridView queryAllRole(RoleVo roleVo) {
        /*传入这两个参数，page会帮我们处理
         * 页数
         * 每页大小*/
        Page<Object> page= PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> data=this.roleDao.queryAllRole(roleVo);
        /*从后台得到总数和所有菜单的数据*/
        return new DataGridView(page.getTotal(),data);
    }

    /*添加一个角色*/
    @Override
    public void addRole(RoleVo roleVo) {
        this.roleDao.insert(roleVo);
    }


    /*指定id角色修改*/
    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleDao.updateOneRole(roleVo);
    }

    /*根据id删除角色*/
    @Override
    public void deleteRole(Integer roleid) {
        /*有关的三个表 */
        /*删除角色表的数据  sys_roles*/
        this.roleDao.deleteRole(roleid);
        //根据角色id删除sys_role_menu里面rid的数据，因为对应的数据也要删除掉
        this.roleDao.deleteRoleMenuByRid(roleid);

        /*根据角色id删除sys_role_user里面的数据*/
        this.roleDao.deleteRoleUserByRid(roleid);

    }
    /*批量删除roleid*/
    @Override
    public void deleteBatchRole(Integer[] ids) {
        /*循环删除，调用上面的删除方法,循环的传入删除的id*/
       for(Integer rid : ids){
           deleteRole(rid);
       }
    }

    /*加载角色管理分配菜单的json*/
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有菜单
        Menu menu=new Menu();
        //SysConstast.AVAILABLE_TRUE=1
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu= menuDao.queryAllMenu(menu);
        //根据角色id查询当前角色拥有的菜单
        List<Menu> roleMenu = menuDao.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleid);

        List<TreeNode> data=new ArrayList<>();
        for(Menu m1 : allMenu){
            /*空的构造方法*/
            /*+""变成字符串*/
           String checkArr=SysConstast.CODE_ZERO+"";
            for(Menu m2 :roleMenu){
                if(m1.getId()==m2.getId()){
                    checkArr=SysConstast.CODE_ONE+"";
                   break;

                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            /*记住这个错,spread在数据库不能为null,只有0和1，要不然判断就报空指针 */
            Boolean spread = m1.getSpread() == SysConstast.SPREAD_TRUE ? true : false;
            /*第一个循环是判断到底有没有选中*/
            data.add(new TreeNode(id,pid,title,spread,checkArr));

        }

        return new DataGridView(data);
    }
    /*保存角色，分配菜单拥有的权限*/
    @Override
    public void savaRoleMenu(RoleVo roleVo) {
       Integer rid=roleVo.getRoleid();
       Integer []mids=roleVo.getIds();
        //根据角色id删除sys_role_menu里面rid的数据，因为对应的数据也要删除掉
        this.roleDao.deleteRoleMenuByRid(rid);
       for(Integer mid : mids){
           this.roleDao.insertRoleMenu(rid,mid);
       }
    }
}
