package com.chm.kd.kq.command;

import com.chm.kd.kq.event.GroupMsg;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupCommand
 * @package com.chm.kd.kq.command
 * @since 2019-07-16
 * description 群组命令接口
 **/
public interface GroupCommand {

  /**
   * 打开群组开关
   *
   * @param groupMsg
   */
  void open(GroupMsg groupMsg);

  /**
   * 关闭群组开关
   *
   * @param groupMsg
   */
  void close(GroupMsg groupMsg);

  /**
   * 设置问题
   *
   * @param groupMsg
   */
  void setQuestions(GroupMsg groupMsg);

  /**
   * 复制问答
   *
   * @param groupMsg
   */
  void copyQuestions(GroupMsg groupMsg);

  /**
   * 查问
   *
   * @paramgroupMsg
   */
  void findQuestions(GroupMsg groupMsg);

  /**
   * 删答
   *
   * @param groupMsg
   */
  void deleteQuestions(GroupMsg groupMsg);

  /**
   * 定时发送
   *
   * @param groupMsg
   */
  void timingSend(GroupMsg groupMsg);

  /**
   * 重复发送
   *
   * @param groupMsg
   */
  void repeatSend(GroupMsg groupMsg);

  /**
   * 删重复发送
   *
   * @param groupMsg
   */
  void deleteRepeatSend(GroupMsg groupMsg);

  /**
   * 设置群管理员
   *
   * @param groupMsg
   */
  void setGroupAdmin(GroupMsg groupMsg);

  /**
   * 问答
   *
   * @param groupMsg
   */
  void answers(GroupMsg groupMsg);
}
