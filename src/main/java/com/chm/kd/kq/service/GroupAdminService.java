package com.chm.kd.kq.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chm.kd.kq.entity.GroupAdmin;

import java.util.List;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupAdminService
 * @package com.chm.kd.kq.service
 * @since 2019-07-18
 * description 群管理控制表 服务类
 **/
public interface GroupAdminService extends IService<GroupAdmin> {

  /**
   * 根据群号和用户QQ获取管理控制对象
   *
   * @param groupId
   * @param userId
   * @return
   */
  GroupAdmin getByGroupIdAndUserId(Long groupId, Long userId);

  /**
   * 查看群管理员
   *
   * @param groupId
   * @return
   */
  List<GroupAdmin> view(Long groupId);

  /**
   * 增加群管理员
   *
   * @param groupId
   * @param userIdList
   * @return
   */
  Long add(Long groupId, List<Long> userIdList);

  /**
   * 删除群管理员
   *
   * @param groupId
   * @param userIdList
   * @return
   */
  Long delete(Long groupId, List<Long> userIdList);

  /**
   * 禁止群管理员
   *
   * @param groupId
   * @param userIdList
   * @return
   */
  Long ban(Long groupId, List<Long> userIdList);

  /**
   * 清空群管理员
   *
   * @param groupId
   * @return
   */
  Long empty(Long groupId);

  /**
   * 查看QQ是否在群号为groupId的群中被禁止
   *
   * @param groupId
   * @param userId
   * @return true 被禁止
   */
  String getRole(Long groupId, Long userId);
}
