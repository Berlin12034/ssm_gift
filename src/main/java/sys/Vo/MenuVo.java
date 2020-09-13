package sys.Vo;

import sys.domian.Menu;

public class MenuVo extends Menu {

    /*写两个分页条件的参数*/
    private Integer page;
    private Integer limit;

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
