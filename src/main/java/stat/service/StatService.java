package stat.service;

import stat.domian.BaseEntity;

import java.util.List;
import java.util.Map;

public interface StatService {
 /*查询客户地区的数据*/
    List<BaseEntity> loadCustomerAreaStatList();
    /*加载业务员年度数据统计数据*/
   List<BaseEntity> loadOpernameYearGradeStat(String year);
    /*加载公司年月季度数据统计数据*/
    List<Double> loadCompanyYearGradeStat(String year);
}
