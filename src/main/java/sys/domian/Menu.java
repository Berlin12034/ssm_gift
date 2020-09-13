package sys.domian;
/*菜单表*/
public class Menu {
   private Integer id;
   private  Integer pid;
   private String title;
   private  String href;
   private  Integer spread;
   private  String target;
   private String  icon;
   private  Integer available;

    public Menu() {
    }

    public Menu(Integer id, Integer pid, String title, String href,Integer spread, String target, String icon, Integer available) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.target = target;
        this.icon = icon;
        this.available = available;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getAvailable() {
        return available;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }

    public Integer getSpread() {
        return spread;
    }

    public void setSpread(Integer spread) {
        this.spread = spread;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", target=" + target +
                ", icon='" + icon + '\'' +
                ", available=" + available +
                '}';
    }
}
