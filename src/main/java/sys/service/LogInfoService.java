package sys.service;

import sys.Vo.LogInfoVo;
import sys.domian.LogInfo;
import sys.utils.DataGridView;

import java.util.List;

public interface LogInfoService {

    /*查询所有日志*/
    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);
    /*添加日志*/
    void addLogInfo(LogInfoVo logInfoVo);


    /*根据id删除日志*/
    void deleteLogInfo(Integer logInfoid);

    /*批量删除日志*/
    void deleteBatchLogInfo(Integer [] ids);


}
