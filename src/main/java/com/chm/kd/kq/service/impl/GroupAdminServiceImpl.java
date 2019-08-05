package com.chm.kd.kq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.kd.kq.entity.GroupAdmin;
import com.chm.kd.kq.mapper.GroupAdminMapper;
import com.chm.kd.kq.service.GroupAdminService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupAdminServiceImpl
 * @package com.chm.kd.kq.service.impl
 * @since 2019-07-18
 * description 群管理控制表 服务实现类
 **/
@Service
public class GroupAdminServiceImpl extends ServiceImpl<GroupAdminMapper, GroupAdmin> implements GroupAdminService {

  @Override
  public GroupAdmin getByGroupIdAndUserId(Long groupId, Long userId) {
    GroupAdmin groupAdmin = this.getOne(Wrappers.<GroupAdmin>lambdaQuery().eq(GroupAdmin::getGroupId, groupId).eq(GroupAdmin::getUserId, userId), false);
    return null != groupAdmin ? groupAdmin : new GroupAdmin();
  }

  @Override
  public List<GroupAdmin> view(Long groupId) {
    return this.list(Wrappers.<GroupAdmin>lambdaQuery().eq(GroupAdmin::getGroupId, groupId));
  }

  @Override
  public Long add(Long groupId, List<Long> userIdList) {
    return userIdList.stream().filter(userId -> {
      GroupAdmin groupAdmin = new GroupAdmin();
      groupAdmin.setGroupId(groupId);
      groupAdmin.setUserId(userId);
      groupAdmin.setType("1");
      return this.save(groupAdmin);
    }).count();
  }

  @Override
  public Long delete(Long groupId, List<Long> userIdList) {
    return userIdList.stream().filter(Objects::nonNull).filter(userId -> this.remove(Wrappers.<GroupAdmin>lambdaQuery().eq(GroupAdmin::getGroupId, groupId).eq(GroupAdmin::getUserId, userId))).count();
  }

  @Override
  public Long ban(Long groupId, List<Long> userIdList) {
    return userIdList.stream().filter(userId -> {
      GroupAdmin groupAdmin = this.getByGroupIdAndUserId(groupId, userId);
      groupAdmin.setGroupId(groupId);
      groupAdmin.setUserId(userId);
      groupAdmin.setType("2");
      return this.saveOrUpdate(groupAdmin);
    }).count();
  }

  @Override
  public Long empty(Long groupId) {
    List<Long> userIdList = view(groupId).stream().map(GroupAdmin::getUserId).collect(Collectors.toList());
    return delete(groupId, userIdList);
  }

  @Override
  public String getRole(Long groupId, Long userId) {
    String type = StringUtils.defaultString(baseMapper.getType(groupId, userId));
    if (StringUtils.equals("1", type)) {
      return "admin";
    } else if (StringUtils.equals("2", type)) {
      return "member";
    }
    return "";
  }
}
