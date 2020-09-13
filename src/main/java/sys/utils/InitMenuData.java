package sys.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sys.dao.MenuDao;
import sys.domian.Menu;

/*初始化菜单数据*/
public class InitMenuData {
    public static void main(String[] args){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中拿到所需要的dao代理对象
        MenuDao menuDao=context.getBean(MenuDao.class);
        menuDao.insert(new Menu(1,0,"礼品店管理系统",null,1,null,"&#xe68e;",1));
        /*礼品店下有四个节点菜单栏*/
        menuDao.insert(new Menu(2,1,"基础管理",null,1,null,"icon-text",1));
        menuDao.insert(new Menu(3,1,"业务管理",null,0,null,"&#xe634;",1));
        menuDao.insert(new Menu(4,1,"系统管理",null,0,null,"&#xe716;",1));
        menuDao.insert(new Menu(5,1,"统计分析",null,0,null,"&#xe630;",1));
        /*基础管理下有两个子节点*/
        menuDao.insert(new Menu(6,2,"客户管理",null,0,null,"&#xe66f;",1));
        menuDao.insert(new Menu(7,2,"礼品管理",null,0,null,"&#xe68f;",1));
       /*业务管理两个子节点*/
        menuDao.insert(new Menu(8,3,"礼品出售",null,0,null,"&#xe657;",1));
        menuDao.insert(new Menu(9,3,"礼品出售单管理",null,0,null,"&#xe627;",1));
        menuDao.insert(new Menu(10,3,"检查单管理",null,0,null,"&#xe63c;",1));

       /*系统管理*/
        menuDao.insert(new Menu(11,4,"菜单管理",null,0,null,"&#xe621;",1));
        menuDao.insert(new Menu(12,4,"角色管理",null,0,null,"&#xe655;",1));
        menuDao.insert(new Menu(13,4,"用户管理",null,0,null,"&#xe655;",1));
        menuDao.insert(new Menu(14,4,"系统公告",null,0,null,"&#xe655;",1));
       /*数据统计*/
        menuDao.insert(new Menu(15,5,"客户地区统计",null,0,null,"&#xe61c;",1));
        menuDao.insert(new Menu(16,5,"客户年度月份销售额曲线图",null,0,null,"&#xe609;",1));
        menuDao.insert(new Menu(17,5,"业务员年度销售额柱状图",null,0,null,"&#xe609;",1));
    System.out.println("初始化完成 ");

    }
}
