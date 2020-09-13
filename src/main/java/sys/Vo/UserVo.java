package sys.Vo;

import sys.domian.User;

public class UserVo extends User {
    /*写两个分页条件的参数*/
    private Integer page;
    private Integer limit;
    /*接受多个角色id，实现批量删除*/
    private Integer[] ids;
    /*登陆页面传来的验证码*/
    private  String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
