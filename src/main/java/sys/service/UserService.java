package sys.service;

import sys.Vo.UserVo;
import sys.domian.User;
import sys.utils.DataGridView;

import java.util.List;

public interface UserService {
    public User findById(Integer uid);

    public List<User> findAll();

    /*用户登陆*/
    User login(UserVo userVo);

    /*查询所有用户*/
    DataGridView queryAllUser(UserVo userVo);
    /*添加用户*/
    void addUser(UserVo userVo);
    /*指定id用户修改*/
    void updateUser(UserVo userVo);

    /*根据id删除用户*/
    void deleteUser(Integer userid);

    /*批量删除用户*/
    void deleteBatchUser(Integer [] ids);

    /*重置密码*/
    public void resetUserPwd(Integer userid);

    /*加载用户管理的分配角色的数据*/
    DataGridView queryUserRole(Integer userid);
    /*保存用户和角色的关系*/
    void savaUserRole(UserVo userVo);
}
