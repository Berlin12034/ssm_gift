package stat.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stat.dao.StatDao;
import stat.domian.BaseEntity;
import stat.service.StatService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatServiceImpl implements StatService {
 @Autowired
  public StatDao statDao;
   /*查询客户地区的数据*/
    @Override
    public List<BaseEntity> loadCustomerAreaStatList() {
        return this.statDao.queryloadCustomerAreaStat();
    }
    /*加载业务员年度数据统计数据*/
    @Override
    public List<BaseEntity> loadOpernameYearGradeStat(String year) {
        return this.statDao.queryOpernameYearGradeStat(year);
    }
    /*加载公司年月季度数据统计数据*/
    @Override
    public List<Double> loadCompanyYearGradeStat(String year) {
        List<Double> baseEntity=new ArrayList<Double>();
        for(int i=1;i<=12;i++){
            /*给月数做声明*/
            String a="0"+i;
        Double price =this.statDao.queryCompanyYearGradeStat(year,a);
        baseEntity.add(price);
        System.out.println(price);
        }
        return baseEntity;
    }
}
