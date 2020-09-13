package sys.serviceimpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.Vo.LogInfoVo;
import sys.dao.LogInfoDao;
import sys.domian.LogInfo;
import sys.domian.Menu;
import sys.service.LogInfoService;
import sys.utils.DataGridView;

import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoDao logInfoDao;

    /*查询所有日志*/
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        /*传入这两个参数，page会帮我们处理
         * 页数
         * 每页大小*/
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data=this.logInfoDao.queryAllLogInfo(logInfoVo);
        /*从后台得到总数和所有日志的数据*/
        return new DataGridView(page.getTotal(),data);
    }

    /*添加日志*/
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        this.logInfoDao.insert(logInfoVo);
    }

    /*根据id删除日志*/
    @Override
    public void deleteLogInfo(Integer logInfoid) {
           this.logInfoDao.deleteLogInfo(logInfoid);
    }

    /*批量删除*/
    @Override
    public void deleteBatchLogInfo(Integer[] ids) {
        for (Integer integer:ids){
            this.deleteLogInfo(integer);
        }

    }
}
