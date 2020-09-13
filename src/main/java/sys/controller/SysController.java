package sys.controller;
/*菜单所有页面跳转控制器*/

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys")
public class SysController {
/*跳转到菜单管理*/
    @RequestMapping("/toMenuManager")
     public String toMenuManager(){
         return "view/system/menu/menuManager";
     }

    /*跳转到菜单管理左边的菜单树页面*/
    @RequestMapping("/toMenuLeft")
    public String toMenuLeft(){
        return "view/system/menu/menuLeft";
    }


    /*跳转菜单管理左边的菜单列表  menu*/
    @RequestMapping("/toMenuRight")
    public String toMenuRight(){
        return "view/system/menu/menuRight";
    }


    /*跳转角色管理页面  role*/
    @RequestMapping("/toRoleManager")
    public String toRoleManager(){
        return "view/system/role/roleManager";
    }

    /*跳转用户管理页面  role*/
    @RequestMapping("/toUserManager")
    public String toUserManager(){
        return "view/system/user/userManager";
    }


    /*跳转日记管理页面  */
    @RequestMapping("/toLogInfoManager")
    public String toLogInfoManager(){
        return "view/system/logInfo/logInfoManager";
    }

    /*跳转到公告管理页面*/
    @RequestMapping("/toNewsManager")
    public String toNewsManager(){
        return "view/system/news/newsManager";
    }

}
