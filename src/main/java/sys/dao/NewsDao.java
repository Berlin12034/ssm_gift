package sys.dao;

import sys.Vo.RoleVo;
import sys.domian.LogInfo;
import sys.domian.News;

import java.util.List;

public interface NewsDao {
    /*查询*/
    List<News> queryAllNews(News news);
    /*插入一条公告栏数据*/
    int insert(News news);

    /*删除公告表的数据*/
    void deleteNews(Integer id);

    /*更新指定id公告方法*/
    void updateNews(News news);

    /*根据id查询公告*/
    News queryNewsById(Integer id);
}
