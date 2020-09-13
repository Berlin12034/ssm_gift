package sys.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.Vo.MenuVo;
import sys.dao.MenuDao;
import sys.domian.Menu;
import sys.service.MenuService;
import sys.utils.DataGridView;

import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {
     @Autowired
     private MenuDao MenuDao;
    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo) {
        return MenuDao.queryAllMenu(menuVo);
    }

    /*根据不同用户拥有不同管理权限返回拥有的菜单管理*/
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo, Integer userId) {
        return MenuDao.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    @Override
    public DataGridView queryAlllMenu(MenuVo menuVo) {
        /*传入这两个参数，page会帮我们处理
        * 页数
        * 每页大小*/
        Page<Object> page= PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data=this.MenuDao.queryAllMenu(menuVo);
        /*从后台得到总数和所有菜单的数据*/
        return new DataGridView(page.getTotal(),data);
    }
/*菜单添加*/
    @Override
    public void addMenu(MenuVo menuVo) {
        this.MenuDao.insert(menuVo);
    }
/*菜单修改*/
    @Override
    public void updateMenu(MenuVo menuVo) {
       this.MenuDao.updateAllMenu(menuVo);
    }

    @Override
    public Integer queryMenuByPid(Integer pid) {
        return this.MenuDao.queryMenuByPid(pid);
    }
   /*根据id删除菜单*/
    @Override
    public void deleteMenu(MenuVo menuVo) {
       /*删除菜单表的数据*/
        this.MenuDao.deleteMenu(menuVo.getId());
        //根据菜单id删除sys_role_menu里面mid的数据，因为对应的数据也要删除掉
        this.MenuDao.deleteRoleMenuByMid(menuVo.getId());
    }


}
