package com.chm.kd.kq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.kd.kq.entity.GroupSwitch;
import com.chm.kd.kq.mapper.GroupSwitchMapper;
import com.chm.kd.kq.service.GroupSwitchService;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupSwitchServiceImpl
 * @package com.chm.kd.kq.service.impl
 * @since 2019-07-15
 * description 群组配置表 服务实现类
 **/
@Service
public class GroupSwitchServiceImpl extends ServiceImpl<GroupSwitchMapper, GroupSwitch> implements GroupSwitchService {

  @Override
  public boolean groupSwitchControl(Long groupId, String switchType, Boolean aSwitch) {
    return this.update(Wrappers.<GroupSwitch>update().set(switchType, aSwitch).lambda().eq(GroupSwitch::getGroupId, groupId));
  }

  @Override
  public boolean groupMainSwitchControl(Long groupId, Boolean mainSwitch) {
    return this.update(Wrappers.<GroupSwitch>lambdaUpdate().set(GroupSwitch::getMainSwitch, mainSwitch).eq(GroupSwitch::getGroupId, groupId));
  }

  @Override
  public boolean groupQuestionSwitchControl(Long groupId, Boolean questionSwitch) {
    return this.update(Wrappers.<GroupSwitch>lambdaUpdate().set(GroupSwitch::getQuestionSwitch, questionSwitch).eq(GroupSwitch::getGroupId, groupId));
  }

  @Override
  public boolean groupJoinSwitchControl(Long groupId, Boolean questionSwitch) {
    return this.update(Wrappers.<GroupSwitch>lambdaUpdate().set(GroupSwitch::getJoinSwitch, questionSwitch).eq(GroupSwitch::getGroupId, groupId));
  }

  @Override
  public boolean groupQuitSwitchControl(Long groupId, Boolean questionSwitch) {
    return this.update(Wrappers.<GroupSwitch>lambdaUpdate().set(GroupSwitch::getQuitSwitch, questionSwitch).eq(GroupSwitch::getGroupId, groupId));
  }

  @Override
  public boolean groupModifyCardSwitchControl(Long groupId, Boolean questionSwitch) {
    return this.update(Wrappers.<GroupSwitch>lambdaUpdate().set(GroupSwitch::getModifyCardSwitch, questionSwitch).eq(GroupSwitch::getGroupId, groupId));
  }


  @Override
  public boolean checkExistByGroupId(Long groupId) {
    return this.count(Wrappers.<GroupSwitch>lambdaQuery().eq(GroupSwitch::getGroupId, groupId)) > 0;
  }

  @Override
  public boolean checkSwitch(Long groupId, String switchType) {
    return this.count(Wrappers.<GroupSwitch>query().eq(switchType, true).lambda().eq(GroupSwitch::getGroupId, groupId)) > 0;
  }

  @Override
  public boolean checkMainSwitch(Long groupId) {
    return this.count(Wrappers.<GroupSwitch>lambdaQuery().eq(GroupSwitch::getMainSwitch, true).eq(GroupSwitch::getGroupId, groupId)) > 0;
  }

  @Override
  public boolean checkQuestionSwitch(Long groupId) {
    return this.count(Wrappers.<GroupSwitch>lambdaQuery().eq(GroupSwitch::getQuestionSwitch, true).eq(GroupSwitch::getGroupId, groupId)) > 0;
  }

  @Override
  public boolean checkSwitchTypeList(Long groupId, Collection<String> switchTypeList) {
    QueryWrapper<GroupSwitch> query = Wrappers.<GroupSwitch>query().eq("GROUP_ID", groupId);
    switchTypeList.forEach(switchType -> query.eq(switchType, true));
    return this.count(query) > 0;
  }
}
