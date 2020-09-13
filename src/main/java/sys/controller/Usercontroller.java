package sys.controller;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.mysql.cj.x.protobuf.MysqlxConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sys.Vo.LogInfoVo;
import sys.Vo.UserVo;
import sys.constast.SysConstast;
import sys.domian.User;
import sys.service.LogInfoService;
import sys.service.UserService;
import sys.utils.WebUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/*用户登陆控制器*/
//controller就是web层
//用@ResponseBody返回的数据是json数据直接在浏览器页面显示了
// @RequestBody是json解析数据存进数据库
/*@PathVariable是路径变量,把前端页面uid的值跟着路径带到这里，然后赋值给参数id  (@PathVariable("uid") int id)*/
@Controller
@RequestMapping("/User")
public class Usercontroller {
    @Autowired
    //Autowired给传的UserService注入值
    private UserService userService;
    @Autowired
    /*分开注入，只能注入一个，要不然会收不到前端传给logInfoVo的值*/
    private LogInfoService logInfoService;
    /*登陆方法*/
    @RequestMapping("/login")
    public String login(UserVo userVo, Model model) {
        String code=WebUtils.getHttpSession().getAttribute("code").toString();
        if(userVo.getCode().equals(code)){
            User user = this.userService.login(userVo);
            if (null != user) {
                //不为空，放进session
                WebUtils.getHttpSession().setAttribute("user", user);
                LogInfoVo logInfoVo = new LogInfoVo();
                /*设置成当前时间*/
                logInfoVo.setLogintime(new Date());
                /*设置用户名*/
                logInfoVo.setLoginname(user.getLoginname());
                /*获取访问的ip地址*/
                logInfoVo.setLoginip(WebUtils.getHttpServletRequest().getRemoteAddr());
                logInfoService.addLogInfo(logInfoVo);
                /*把user数据放到model带到前端页面*/
                model.addAttribute("user", user);
                //跳到首页
                return "view/system/index";
            } else {
                //跳回登陆页面
                model.addAttribute("error", SysConstast.USER_LOGIN_ERROR_MSG);
                return "view/system/login";
            }
        }else {
            //跳回登陆页面
            model.addAttribute("error", SysConstast.USER_CODELOGIN_ERROR_MSG);
            return "view/system/login";
        }



    }

    /*得到验证码*/
    @RequestMapping("getCode")
    public void getCode (HttpServletResponse respose, HttpSession session) throws IOException {
         /*定义验证码的长和宽,codeCount是验证码个数*/
    LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(116,36,5,5);
      /*respose不能用工具类，所有输出不出去，所以用session*/
        session.setAttribute("code",lineCaptcha.getCode());

        ServletOutputStream outputStream=respose.getOutputStream();
        /*把outputStream这个流写进去*/
        ImageIO.write(lineCaptcha.getImage(),"JPEG",outputStream);
    }

}
