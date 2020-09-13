package sys.dao;

import sys.Vo.LogInfoVo;
import sys.domian.LogInfo;
import sys.domian.Menu;

import java.util.List;

public interface LogInfoDao {
    /*查询日志*/
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);

    /*插入一条日志栏数据*/
    int insert(LogInfo logInfo);

    /*删除日志表的数据*/
    void deleteLogInfo(Integer id);
}
