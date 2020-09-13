package sys.service;

import sys.Vo.NewsVo;
import sys.domian.News;
import sys.utils.DataGridView;

public interface NewsService {

    /*查询所有公告*/
    DataGridView queryAllNews(NewsVo newsVo);
    /*添加公告*/
    void addNews(NewsVo newsVo);

    /*修改公告*/
    void updateNews(NewsVo newsVo);

    /*根据id删除公告*/
    void deleteNews(Integer newsid);

    /*批量删除公告*/
    void deleteBatchNews(Integer[] ids);

    /*根据id查询公告*/
    News queryNewsById(Integer id);
}
