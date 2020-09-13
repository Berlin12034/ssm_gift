package sys.test;

import sys.domian.items;
import sys.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class  itemsTest {
/*    @Autowired
    private UserService service;
    @Test
    public  void findById(){
        //获取容器
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        //从容器中拿到所需要的dao代理对象
        //Dao dao=ac.getBean(Dao.class);
      //items s  =dao.findById(1);
      //System.out.println(s.getAddress());
        //dao调用方法


        //service测试
        service  =ac.getBean(UserService.class);
        items s  =service.findById(1);
        System.out.println(s.getAddress());
    }
  *//*查找列表*//*
   @Test
    public void findAll(){
       ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
       service  =ac.getBean(UserService.class);
      List<items> items=service.findAll();
      System.out.println(items);
    }

    *//*更新用户*//*
  @Test
   public void updateitems(){
      ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
      service  =ac.getBean(UserService.class);
      items s  =service.findById(1);
      System.out.println("修改当前用户为"+s);
      s.setAddress("背景");
      service.updateitems(s);
      *//*在查一下修改过后的用户*//*
      s  =service.findById(1);
      System.out.println("修改过后用户为"+s);
   }
*//*删除用户*//*
   @Test
    public void deleteByItems(){
       ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
       service  =ac.getBean(UserService.class);
       service.deleteById(1);
       System.out.println("被删除");
   }*/
}
