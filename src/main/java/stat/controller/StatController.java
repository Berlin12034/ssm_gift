package stat.controller;

import bus.Vo.CustomerVo;
import bus.domian.Customer;
import bus.domian.Sale;
import bus.service.CustomerService;
import bus.service.SaleService;

import java.io.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import stat.domian.BaseEntity;
import stat.service.StatService;
import stat.utils.ExprotCustomerUtils;
import stat.utils.ExprotSaleUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*统计分析的缩写*/
@RequestMapping("stat")
/*相应的都是jsaon数据*/
@Controller
public class StatController {
    @Autowired
    public StatService statService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private SaleService saleService;
    /*跳转到客户统计分析页面*/
    @RequestMapping("toCustomerAreaStat")
    public String toCustomerAreaStat() {
        return "view/stat/customerAreaStat";
    }

    /*加载客户地区数据*/
    @RequestMapping("loadCustomerAreaStatJosn")
    @ResponseBody
    public List<BaseEntity> loadCustomerAreaStatJosn() {
        return this.statService.loadCustomerAreaStatList();
    }

    /*跳转到业务员年度统计分析页面*/
    @RequestMapping("toOpernameYearGradeStat")
    public String toOpernameYearGradeStat() {
        return "view/stat/opernameYearGradeStat";
    }

    /*加载业务员年度数据统计数据*/
    @RequestMapping("loadOpernameYearGradeStat")
    @ResponseBody
    public Map<String, Object> opernameYearGradeStat(String year) {
        List<BaseEntity> entities = this.statService.loadOpernameYearGradeStat(year);
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> names = new ArrayList<String>();
        List<Double> values = new ArrayList<Double>();
        for (BaseEntity baseEntity : entities) {
            names.add(baseEntity.getName());
            values.add(baseEntity.getValue());
        }
        map.put("name", names);
        map.put("value", values);
        return map;
    }


    /*跳转公司年月度统计分析页面*/
    @RequestMapping("toCampanyYearGradeStat")
    public String toCampanyYearGradeStat() {
        return "view/stat/companyYearGradeStat";
    }

    /*加载公司年月季度数据统计数据*/
    @RequestMapping("loadCompanyYearGradeStat")
    @ResponseBody
    public List<Double> loadCompanyYearGradeStat(String year) {
        List<Double> entities = this.statService.loadCompanyYearGradeStat(year);
        return entities;
    }

    /*导出客户数据*/
    @RequestMapping("exportCustomer")
    public ResponseEntity<Object> exportCustomer(CustomerVo customerVo, HttpServletResponse response) {
        List<Customer> customer = this.customerService.queryCustomerAllList(customerVo);
        String filename = "客户数据.xls";
        String sheetName = "客户数据";
         ByteArrayOutputStream bos = ExprotCustomerUtils.exportCustomer(customer, sheetName);

        try {
             filename = URLEncoder.encode(filename, "UTF-8");//处理文件名乱码
            //创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            header.setContentDispositionFormData("attachment", filename);
            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }

    /*导出出售单数据*/
    @RequestMapping("exportSale")
    public ResponseEntity<Object> exportSale(String saleid) {
        //根据出售单号查询出售单信息
        Sale sale=saleService.queryOneById(saleid);
        //根据身份证号查询客户信息
        Customer customer = this.customerService.queryCustomerByIdentity(sale.getIdentity());
        String filename = customer.getCustname()+"出售数据.xls";
        String sheetName = "客户订单数据";
        ByteArrayOutputStream bos = ExprotSaleUtils.exportSale(sale,customer,sheetName);

        try {
            filename = URLEncoder.encode(filename, "UTF-8");//处理文件名乱码
            //创建封装响应头信息的对象
            HttpHeaders header = new HttpHeaders();
            //封装响应内容类型(APPLICATION_OCTET_STREAM 响应的内容不限定)
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //设置下载的文件的名称
            header.setContentDispositionFormData("attachment", filename);
            return new ResponseEntity<Object>(bos.toByteArray(), header, HttpStatus.CREATED);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;


    }

    @RequestMapping("/employee/File/download")
    public String  testDownload(HttpServletResponse response , Model model) {
        //待下载文件名
        String fileName = "1.png";
        //设置为png格式的文件
        /*response.setHeader("content-type", "image/png");*/
        /*response.setContentType("application/octet-stream");*/
        //导出txt文件
        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".txt");
        byte[] buff = new byte[1024];
        //创建缓冲输入流
        /*BufferedInputStream bis = null;
        OutputStream outputStream = null;*/

        BufferedOutputStream buff4 = null;
        /*StringBuffer write = new StringBuffer();*/
        ServletOutputStream outSTr = null;

        try {
            StringBuffer write = new StringBuffer("sssdssvsvvvvv");
            /*outputStream = response.getOutputStream();*/

            outSTr = response.getOutputStream();// 建立
            buff4 = new BufferedOutputStream(outSTr);

            //这个路径为待下载文件的路径
            /*bis = new BufferedInputStream(new FileInputStream(new File("D:/upload/" + fileName )));
            int read = bis.read(buff);*/

            //通过while循环写入到指定了的文件夹中
            /*while (read != -1) {*/
            buff4.write(write.toString().getBytes("UTF-8"));
            buff4.flush();
            buff4.close();
         /*   outputStream.write(buff, 0, buff.length);
                outputStream.flush();*/
               /* read = bis.read(buff);*/
           /* }*/
        } catch (IOException e ) {
            e.printStackTrace();
            //出现异常返回给页面失败的信息
            model.addAttribute("result","下载失败");
        } finally {
            System.out.println("dd");
           /* if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
            /*if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }*/
        }
        //成功后返回成功信息
        model.addAttribute("result","下载成功");
        return "employee/EmployeeDownloadFile";
    }
}