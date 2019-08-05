package com.chm.kd.kq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chm.kd.kq.entity.GroupPrompt;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupPromptService
 * @package com.chm.kd.kq.service
 * @since 2019-07-25
 * description 提示语表 服务类
 **/
public interface GroupPromptService extends IService<GroupPrompt> {

  /**
   * 获取加群提示语
   *
   * @param groupId
   * @return
   */
  String getGroupJoinPrompt(Long groupId);

  /**
   * 获取加群提示语
   *
   * @param groupId
   * @return
   */
  String getGroupModifyCardPrompt(Long groupId);

  /**
   * 根据群号和类型获取提示语
   *
   * @param groupId
   * @param type
   * @return
   */
  String getContentByGroupIdAndType(Long groupId, String type);

  /**
   * 根据群号和类型获取提示语
   *
   * @param groupId
   * @param type
   * @return
   */
  GroupPrompt getByGroupIdAndType(Long groupId, String type);

  /**
   * 设置提示语
   *
   * @param groupId
   * @param type
   * @param content
   * @return
   */
  boolean setByGroupIdAndTypeAndContent(Long groupId, String type, String content);
}
