package stat.dao;

import org.apache.ibatis.annotations.Param;
import stat.domian.BaseEntity;

import java.util.List;

public interface StatDao {
    /*查询客户地区的数据*/
    List<BaseEntity> queryloadCustomerAreaStat();
    /*加载业务员年度数据统计数据*/
    List<BaseEntity> queryOpernameYearGradeStat(String year);
    /*加载公司年月季度数据统计数据*/
    Double queryCompanyYearGradeStat(@Param("y") String year,@Param("moth") String moth);
}
