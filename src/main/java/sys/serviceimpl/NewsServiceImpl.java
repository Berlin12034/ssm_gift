package sys.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.Vo.NewsVo;
import sys.dao.NewsDao;
import sys.domian.News;
import sys.service.NewsService;
import sys.utils.DataGridView;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsDao newsDao;

    /*查询所有公告*/
    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        /*传入这两个参数，page会帮我们处理
         * 页数
         * 每页大小*/
        Page<Object> page= PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> data=this.newsDao.queryAllNews(newsVo);
        /*从后台得到总数和所有公告的数据*/
        return new DataGridView(page.getTotal(),data);
    }

    /*添加公告*/
    @Override
    public void addNews(NewsVo NewsVo) {
        this.newsDao.insert(NewsVo);
    }

    /*公告修改*/
    @Override
    public void updateNews(NewsVo newsVo) {
        this.newsDao.updateNews(newsVo);
    }

    /*根据id删除公告*/
    @Override
    public void deleteNews(Integer Newsid) {
           this.newsDao.deleteNews(Newsid);
    }

    /*批量删除*/
    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer integer:ids){
            this.deleteNews(integer);
        }

    }

    /*根据id查询公告*/
    @Override
    public News queryNewsById(Integer id) {
        return this.newsDao.queryNewsById(id);
    }
}
