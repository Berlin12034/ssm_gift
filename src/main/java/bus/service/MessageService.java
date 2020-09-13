package bus.service;

import bus.Vo.MessageVo;
import bus.domian.Message;
import sys.utils.DataGridView;

import java.util.List;

public interface MessageService {

    /*查询所有礼品信息*/
    DataGridView queryAllMessage(MessageVo messageVo);
    /*添加礼品信息*/
    void addMessage(MessageVo messageVo);

    /*修改礼品信息*/
    void updateMessage(MessageVo messageVo);

    /*根据id删除礼品信息*/
    void deleteMessage(String giftnumber);

    /*批量删除礼品信息*/
    void deleteBatchMessage(String[] giftnumber);
//匹配礼品名和类型
    List<Message> queryOnemessage(MessageVo messageVo);

    /*根据礼品型号查询*/
    Message queryMessageByGiftNumber(String giftnumber);
  /*从查出相同礼品名字和礼品类型去更新*/
    void updateOneMessage(MessageVo messageVo);

    /*下单库存数量的变动*/
    void updateMessageNumber(Message message);
}
