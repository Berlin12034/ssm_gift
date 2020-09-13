package sys.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import sys.Vo.UserVo;
import sys.constast.SysConstast;
import sys.dao.Dao;
import sys.dao.RoleDao;
import sys.domian.Role;
import sys.domian.User;
import sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.utils.DataGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceimpl implements UserService {
    @Autowired
    //自动注入一个dao
    private Dao dao;
    @Autowired
    private RoleDao roleDao;
    public User findById(Integer uid) {
        return dao.findById(uid);
    }
    public List<User> findAll(){
     return dao.findAll();
    };



    /*用户登陆*/
    public User login(UserVo userVo){
         return dao.login(userVo);
    }

    /*查询所有用户*/
    @Override
    public DataGridView queryAllUser(UserVo userVo) {
        Page<Object> page= PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        List<User> data=this.dao.queryAllUser(userVo);

        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addUser(UserVo userVo) {
      //默认密码123456
        userVo.setPassword(SysConstast.USER_DEFAULT_PWD);
        //设置用户类型 都是普通用户type=2,我是我加的我是超级管理员
       userVo.setType(SysConstast.USER_TYPE_MORMAL);
       this.dao.insert(userVo);
    }

    /*更新用户*/
    @Override
    public void updateUser(UserVo userVo) {
        this.dao.updateOneUser(userVo);
    }

    /*删除一个用户*/
    @Override
    public void deleteUser(Integer userid) {
          //删除用户
        this.dao.deleteUser(userid);
        //根据用户id删除sys_role_user里面的数据
        this.roleDao.deleteRoleUserByUid(userid);
    }

    /*用户批量删除*/
    @Override
    public void deleteBatchUser(Integer[] ids) {
             for(Integer uid : ids){
                 this.deleteUser(uid);
             }
    }

    /*重置密码*/
    @Override
    public void resetUserPwd(Integer userid) {
       User user  =new User();
       user.setUserid(userid);
       //设置默认密码
        user.setPassword(SysConstast.USER_DEFAULT_PWD);
        //更新他
        this.dao.resetUserPwd(user);
    }

    /*加载用户管理的分配角色的数据*/
    @Override
    public DataGridView queryUserRole(Integer userid) {
        //1.查询所有可用的角色
        Role role=new Role();
        role.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Role> allRole=this.roleDao.queryAllRole(role);
        //2.根据用户id查询已经拥有的角色
        List<Role> userRole=this.roleDao.queryRoleByUid(SysConstast.AVAILABLE_TRUE,userid);

        List<Map<String,Object>> data=new ArrayList<>();
        for(Role r1 : allRole){
            /*LAY_CHECKED这是表格的一个属性，就是显示表格选项框打勾
            * 这里是让那些被分配管理的打勾*/
            Boolean LAY_CHECKED=false;
            for(Role r2 : userRole){
                if(r1.getRoleid()==r2.getRoleid()){
                    LAY_CHECKED=true;
                }
            }
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("roleid",r1.getRoleid());
            map.put("rolename",r1.getRolename());
            map.put("roledesc",r1.getRoledesc());
            map.put("LAY_CHECKED",LAY_CHECKED);
            data.add(map);
        }
        return new DataGridView(data);
    }

    /*保存用户和角色的关系*/
    @Override
    public void savaUserRole(UserVo userVo) {
      Integer userid=userVo.getUserid();
      /*前端页面传有ids*/
      Integer[] ids=userVo.getIds();
      /*根据用户id删除sys_role_user里面的数据*/
        /*因为之前数据库中有相同发数据不能重复插入，因为
        * 你查出来显示打勾，你又重复保存，会保存失败所以先删除之前保存的所有
        * 再保存现在选中的*/
        this.roleDao.deleteRoleUserByUid(userid);
        //保存
        if(ids!=null&&ids.length>0){
            for(Integer rid :ids){
                this.dao.insertUserRole(userid,rid);
            }
        }
    }


}
