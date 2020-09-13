package bus.Vo;

import bus.domian.Customer;
import bus.domian.Message;

public class MessageVo extends Message {

    /*写两个分页条件的参数*/
    private Integer page;
    private Integer limit;
    /*接受多个客户id，实现批量删除*/
    private String[] ids;

    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
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
}
