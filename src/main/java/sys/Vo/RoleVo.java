package sys.Vo;

import sys.domian.Role;

public class RoleVo extends Role {
    /*写两个分页条件的参数*/
    private Integer page;
    private Integer limit;
    /*接受多个角色id，实现批量删除*/
    private Integer[] ids;

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
}
