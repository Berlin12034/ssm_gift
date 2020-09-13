package bus.dao;

import bus.Vo.MessageVo;
import bus.domian.Message;

import java.util.List;

public interface MessageDao {
    /*查询礼品所有信息*/
    List<Message> queryAllMessage(Message message);

    /*添加一条礼品信息*/
    void insert(MessageVo messageVo);
    /*更新一条礼品信息*/
    void updateMessage(MessageVo messageVo);

   /*删除一条礼品信息*/
    void deleteMessage(String giftnumber);

    /*匹配出礼品名和类型*/
    List<Message> queryOneMessage(MessageVo messageVo);

    /*根据礼品型号查询*/
    Message queryMessageByGiftNumber(String giftnumber);

    /*从查出相同礼品名字和礼品类型去更新*/
    void updateOneMessage(MessageVo messageVo);
    /*下单库存数量的变动*/
    void updateMessageNumber(Message message);
}
