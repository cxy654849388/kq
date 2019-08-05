package com.chm.kd.kq.api;

import com.chm.kd.kq.event.Anonymous;
import com.chm.kd.kq.info.GroupInfo;
import com.chm.kd.kq.info.GroupMemberInfo;

import java.util.List;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupApi
 * @package com.chm.kd.kq.api
 * @since 2019-07-12
 * description 群api
 **/
public interface GroupApi {

  /**
   * 群组踢人
   *
   * @param groupId 群号
   * @param userId  要踢的 QQ 号
   */
  void setGroupKick(Long groupId, Long userId);

  /**
   * 群组单人禁言
   *
   * @param groupId  群号
   * @param userId   要禁言的 QQ 号
   * @param duration 禁言时长，单位秒，0 表示取消禁言 ，默认值30 * 60
   */
  void setGroupBan(Long groupId, Long userId, Integer duration);

  /**
   * 群组匿名用户禁言
   *
   * @param groupId   群号
   * @param anonymous 要禁言的匿名用户对象（群消息上报的 anonymous 字段）
   * @param duration  禁言时长，单位秒，无法取消匿名用户禁言 默认值30 * 60
   */
  void setGroupAnonymousBan(Long groupId, Anonymous anonymous, Integer duration);

  /**
   * 群组匿名用户禁言
   *
   * @param groupId  群号
   * @param flag     要禁言的匿名用户的 flag（需从群消息上报的数据中获得)
   * @param duration 禁言时长，单位秒，无法取消匿名用户禁言 默认值30 * 60
   */
  void setGroupAnonymousBan(Long groupId, String flag, Integer duration);

  /**
   * 群组全员禁言
   *
   * @param groupId 群号
   * @param enable  是否禁言 默认值 true
   */
  void setGroupWholeBan(Long groupId, Boolean enable);

  /**
   * 群组设置管理员
   *
   * @param groupId 群号
   * @param userId  要设置管理员的 QQ 号
   * @param enable  true 为设置，false 为取消 默认值 true
   */
  void setGroupWholeBan(Long groupId, Long userId, Boolean enable);


  /**
   * 群组匿名
   *
   * @param groupId 群号
   * @param enable  是否允许匿名聊天 默认 true
   */
  void setGroupAnonymous(Long groupId, Boolean enable);

  /**
   * 设置群名片（群备注）
   *
   * @param groupId 群号
   * @param userId  要设置的 QQ 号
   * @param card    群名片内容，不填或空字符串表示删除群名片
   */
  void setGroupCard(Long groupId, Long userId, String card);

  /**
   * 退出群组
   *
   * @param groupId   群号
   * @param isDismiss 是否解散，如果登录号是群主，则仅在此项为 true 时能够解散
   */
  void setGroupLeave(Long groupId, Boolean isDismiss);

  /**
   * 设置群组专属头衔
   *
   * @param groupId      群号
   * @param userId       要设置的 QQ 号
   * @param specialTitle 专属头衔，不填或空字符串表示删除专属头衔
   */
  void setGroupSpecialTitle(Long groupId, Long userId, String specialTitle);

  /**
   * 获取群列表
   *
   * @return
   */
  List<GroupInfo> getGroupList();

  /**
   * 获取群成员信息
   *
   * @param groupId
   * @param userId
   * @return
   */
  GroupMemberInfo getGroupMemberInfo(Long groupId, Long userId);

  /**
   * 获取群成员列表
   *
   * @param groupId 群号
   * @return
   */
  List<GroupMemberInfo> getGroupMemberList(Long groupId);

  /**
   * 发送加群提示
   *
   * @param groupId
   * @param userId
   */
  void sendJoinGroupPrompt(Long groupId, Long userId);

  /**
   * 发送退群提示
   *
   * @param groupId
   * @param userId
   */
  void sendQuitGroupPrompt(Long groupId, Long userId);

  /**
   * 发送改名提示
   *
   * @param groupId
   * @param userId
   */
  void sendModifyCardPrompt(Long groupId, Long userId);

  /**
   * 发送提示语
   *
   * @param groupId
   * @param prompt
   */
  void sendPromptView(Long groupId, String prompt);

  /**
   * 发送提示语
   *
   * @param groupId
   * @param userId
   */
  void sendRemind(Long groupId, Long userId);
}
