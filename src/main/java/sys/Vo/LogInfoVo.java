package sys.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import sys.domian.LogInfo;
/*就因为导了这个import javax.xml.crypto.Data;导致springmvc前台转入的string无法转成date数据*/

import java.util.Date;

public class LogInfoVo extends LogInfo {

    /*写两个分页条件的参数*/
    private Integer page;
    private Integer limit;
    /*接受多个id，实现批量删除*/
    private Integer[] ids;

    /*扩展时间参数*/
    /*这里是从前台接收数据到后台存储的*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

