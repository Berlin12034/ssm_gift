package sys.controller;
/*公告管理的控制器*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.Vo.NewsVo;
import sys.domian.News;
import sys.domian.User;
import sys.service.NewsService;
import sys.utils.DataGridView;
import sys.utils.ResultObj;
import sys.utils.WebUtils;

import java.util.Date;


@RestController
/*这个控制器是返回json数据的,不用在写responsebody*/
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;


    /*加载公告列表返回DataGridView*/
     @RequestMapping("/loadAllNews")
     public DataGridView loadAllNews(NewsVo newsVo) {
         return this.newsService.queryAllNews(newsVo);
     }

     /*添加公告
     * 类名在前就是返回这个类的数据*/
     @RequestMapping("/addNews")
    public ResultObj addNews(NewsVo newsVo){
       try{
           /*把系统时间弄进去*/
           User user= (User) WebUtils.getHttpSession().getAttribute("user");
           newsVo.setCreatetime(new Date());
           /*把当前登陆者传进去*/
           newsVo.setOpername(user.getName());
           this.newsService.addNews(newsVo);
           /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
           return ResultObj.ADD_SUCCESS;
       } catch (Exception e){
           return ResultObj.ADD_ERROR;
       }


    }

    /*修改公告
     * 类名在前就是返回这个类的数据*/
    @RequestMapping("/updateNews")
    public ResultObj updateNews(NewsVo newsVo){
        try{
            /*把系统时间弄进去*/
            User user= (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getName());
            this.newsService.updateNews(newsVo);
            /*算是调用了ResultObj的静态方法里有两个固定的参数msg 和code*/
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e){
            return ResultObj.UPDATE_ERROR;
        }


    }

    /*删除公告*/
    @RequestMapping("deleteNews")
    public ResultObj deleteNews(NewsVo newsVo){
       try {
           this.newsService.deleteNews(newsVo.getId());
           return ResultObj.DELETE_SUCCESS;
       }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*批量删除公告*/
    @RequestMapping("deleteBatchNews")
    public ResultObj deleteBatchNews(NewsVo newsVo){
        try {
            this.newsService.deleteBatchNews(newsVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /*根据id查询公告*/

    @RequestMapping("loadNewsById")
    public News loadNewsById(Integer id){
        return this.newsService.queryNewsById(id);
    }
}

