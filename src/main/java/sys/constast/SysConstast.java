package sys.constast;
/*常量接口*/
public interface SysConstast {
    String USER_LOGIN_ERROR_MSG="用户名密码不正确";

   /*可用状态*/
    Integer AVAILABLE_TRUE=1;
    Integer AVAILABLE_FALSE=0;

 /*用户可用类型 1是超级管理员 2是普通会员*/
    Integer USER_TYPE_SUPER=1;
    Integer USER_TYPE_MORMAL=2;

    /*是否展开 1是展开 0不开*/
    Integer SPREAD_TRUE= 1;
    Integer SPREAD_FALSE= 0;

    /*操作状态*/
    String ADD_SUCCESS="添加成功";
    String ADD_ERROR="添加失败";
    String ADD_ERROR1="添加失败,库存不足";

    String UPDATE_SUCCESS="更新成功";
    String UPDATE_ERROR="更新失败";


    String RESET_SUCCESS="重置成功";
    String RESET_ERROR="重置失败";

    String DELETE_SUCCESS="删除成功";
    String DELETE_ERROR="删除失败";


    String DISPATCH_SUCCESS="分配成功";
    String DISPATCH_ERROR="分配失败";

    Integer CODE_SUCCESS=0;//操作成功
    Integer CODE_ERROR=-1;//失败


   /*公用常量*/
    Integer CODE_ZERO=0;
    Integer CODE_ONE =1;
    Integer CODE_TWO =2;
   Integer CODE_THREE=3;

   /*String USER_DEFAULT_PWD默认密码*/
    String USER_DEFAULT_PWD="123456";
    /*临时文件标记*/
    String FILE_UPLOAD_TEMP ="_temp";

    /*图片 默认地址*/
    Object DEFAULT_CAR_IMG ="images/defaultcarimg.jsp";
    /*存在数据更改失败 */


    String UPDATE_CUNERROR ="更新失败，数据已经存在,请根据存在数据更改";

    /*单号的前缀*/
    String CAR_ORDER_CZ = "CZ";
    String CAR_ORDER_JC = "JC";
    /*更新失败，库存数量不足*/
    String UPDATE_ERROR1 ="更新失败，库存数量不足";
    String USER_CODELOGIN_ERROR_MSG = "验证码不正确";
}
