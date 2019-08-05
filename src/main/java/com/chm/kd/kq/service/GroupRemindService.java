package com.chm.kd.kq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chm.kd.kq.entity.GroupRemind;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupRemindService
 * @package com.chm.kd.kq.service
 * @since 2019-07-30
 * description 群提醒配置表 服务类
 **/
public interface GroupRemindService extends IService<GroupRemind> {

  /**
   * 根据群号和用户号获取提醒配置
   *
   * @param groupId
   * @param userId
   * @return
   */
  GroupRemind getByGroupIdAndUserId(Long groupId, Long userId);

}
