package sys.dao;

import org.apache.ibatis.annotations.Param;
import sys.domian.Role;
import sys.domian.User;

import java.util.List;

public interface Dao {
    /*查询用户根据id*/
    public User findById(Integer uid);

    /*查询用户列表*/

    public List<User> findAll();



    /*用户登陆方法*/
    User login(User user);

    /*查询所有用户*/
    List<User> queryAllUser(User user);

    /*添加用户*/
    /*插入一条菜单栏数据*/
    int insert(User user);

    /*
     * 更新用户
     * */
    public void updateOneUser(User user);

    /*删除用户的数据*/
    void deleteUser(Integer userid);

    public void resetUserPwd(User user);

    /*保存用户选中分配的角色*/
    void insertUserRole(@Param("uid")Integer userid, @Param("rid")Integer rid);
}
