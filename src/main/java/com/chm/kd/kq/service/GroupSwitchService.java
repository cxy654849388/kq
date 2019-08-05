package com.chm.kd.kq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chm.kd.kq.entity.GroupSwitch;

import java.util.Collection;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupSwitchService
 * @package com.chm.kd.kq.service
 * @since 2019-07-15
 * description 群组配置表 服务类
 **/
public interface GroupSwitchService extends IService<GroupSwitch> {

  /**
   * 控制群开关
   *
   * @param groupId    Q群号
   * @param switchType 开关类别
   * @param aSwitch    开关
   * @return
   */
  boolean groupSwitchControl(Long groupId, String switchType, Boolean aSwitch);

  /**
   * 控制群总开关
   *
   * @param groupId    Q群号
   * @param mainSwitch 开关
   * @return
   */
  boolean groupMainSwitchControl(Long groupId, Boolean mainSwitch);

  /**
   * 控制群问答开关
   *
   * @param groupId        Q群号
   * @param questionSwitch 开关
   * @return
   */
  boolean groupQuestionSwitchControl(Long groupId, Boolean questionSwitch);

  /**
   * 控制群加群提示开关
   *
   * @param groupId
   * @param questionSwitch
   * @return
   */
  boolean groupJoinSwitchControl(Long groupId, Boolean questionSwitch);

  /**
   * 控制群退群提示开关
   *
   * @param groupId
   * @param questionSwitch
   * @return
   */
  boolean groupQuitSwitchControl(Long groupId, Boolean questionSwitch);

  /**
   * 控制群改名提示开关
   *
   * @param groupId
   * @param questionSwitch
   * @return
   */
  boolean groupModifyCardSwitchControl(Long groupId, Boolean questionSwitch);


  /**
   * 检查群开关配置是否存在
   *
   * @param groupId
   * @return
   */
  boolean checkExistByGroupId(Long groupId);

  /**
   * 检查Q群开关是否开启
   *
   * @param groupId
   * @param switchType
   * @return
   */
  boolean checkSwitch(Long groupId, String switchType);

  /**
   * 检查Q群总开关是否开启
   *
   * @param groupId
   * @return
   */
  boolean checkMainSwitch(Long groupId);

  /**
   * 检查Q群问答开关是否开启
   *
   * @param groupId
   * @return
   */
  boolean checkQuestionSwitch(Long groupId);

  /**
   * 检查Q群指定开关列表是否全部开启
   *
   * @param groupId
   * @param switchTypeList
   * @return
   */
  boolean checkSwitchTypeList(Long groupId, Collection<String> switchTypeList);
}
