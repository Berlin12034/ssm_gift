package bus.serviceimpl;

import bus.Vo.MessageVo;
import bus.dao.MessageDao;
import bus.domian.Message;
import bus.service.MessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sys.constast.SysConstast;
import sys.utils.AppFileUtils;
import sys.utils.DataGridView;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    /*查询所有礼品信息*/
    @Override
    public DataGridView queryAllMessage(MessageVo messageVo) {
        /*传入这两个参数，page会帮我们处理
         * 页数
         * 每页大小*/
        Page<Object> page= PageHelper.startPage(messageVo.getPage(),messageVo.getLimit());
        List<Message> data=this.messageDao.queryAllMessage(messageVo);
        /*从后台得到总数和所有礼品信息的数据*/
        return new DataGridView(page.getTotal(),data);
    }

    /*添加礼品信息*/
    @Override
    public void addMessage(MessageVo MessageVo) {
        this.messageDao.insert(MessageVo);
    }

    /*礼品信息修改*/
    @Override
    public void updateMessage(MessageVo messageVo) {
        this.messageDao.updateMessage(messageVo);
    }

    /*根据id删除礼品信息*/
    @Override
    public void deleteMessage(String giftnumber) {
        /*先删除图片*/
           Message message=messageDao.queryMessageByGiftNumber(giftnumber);
           /*如果不是默认图片就删除*/
           if(!message.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
               AppFileUtils.deleteFileUsePath(message.getCarimg());
           }

         //删除数据库数据
           this.messageDao.deleteMessage(giftnumber);
    }

    /*批量删除*/
    @Override
    public void deleteBatchMessage(String[] giftnumbers) {
        for (String giftnumber:giftnumbers){
            this.deleteMessage(giftnumber);
        }

    }
/*匹配出礼品名和类型*/

    @Override
    public List<Message> queryOnemessage(MessageVo messageVo) {
        return this.messageDao.queryOneMessage(messageVo);
    }

    /*根据礼品型号查询*/
    @Override
    public Message queryMessageByGiftNumber(String giftnumber) {
        return this.messageDao.queryMessageByGiftNumber(giftnumber);
    }
    /*从查出相同礼品名字和礼品类型去更新*/
    @Override
    public void updateOneMessage(MessageVo messageVo) {
        this.messageDao.updateOneMessage(messageVo);
    }

    /*下单库存数量的变动*/
    @Override
    public void updateMessageNumber(Message message) {
        this.messageDao.updateMessageNumber(message);
    }


}
