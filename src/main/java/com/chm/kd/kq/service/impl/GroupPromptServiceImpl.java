package com.chm.kd.kq.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.kd.kq.entity.GroupPrompt;
import com.chm.kd.kq.mapper.GroupPromptMapper;
import com.chm.kd.kq.service.GroupPromptService;
import org.springframework.stereotype.Service;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupPromptServiceImpl
 * @package com.chm.kd.kq.service.impl
 * @since 2019-07-25
 * description 提示语表 服务实现类
 **/
@Service
public class GroupPromptServiceImpl extends ServiceImpl<GroupPromptMapper, GroupPrompt> implements GroupPromptService {

  @Override
  public String getGroupJoinPrompt(Long groupId) {
    GroupPrompt groupPrompt = this.getOne(Wrappers.<GroupPrompt>lambdaQuery().eq(GroupPrompt::getGroupId, groupId).eq(GroupPrompt::getType, 1), false);
    return null != groupPrompt ? groupPrompt.getContent() : null;
  }

  @Override
  public String getGroupModifyCardPrompt(Long groupId) {
    GroupPrompt groupPrompt = this.getOne(Wrappers.<GroupPrompt>lambdaQuery().eq(GroupPrompt::getGroupId, groupId).eq(GroupPrompt::getType, 3), false);
    return null != groupPrompt ? groupPrompt.getContent() : null;
  }

  @Override
  public String getContentByGroupIdAndType(Long groupId, String type) {
    return this.getByGroupIdAndType(groupId, type).getContent();
  }

  @Override
  public GroupPrompt getByGroupIdAndType(Long groupId, String type) {
    GroupPrompt groupPrompt = this.getOne(Wrappers.<GroupPrompt>lambdaQuery().eq(GroupPrompt::getGroupId, groupId).eq(GroupPrompt::getType, type), false);
    return null != groupPrompt ? groupPrompt : new GroupPrompt();
  }

  @Override
  public boolean setByGroupIdAndTypeAndContent(Long groupId, String type, String content) {
    GroupPrompt groupPrompt = this.getByGroupIdAndType(groupId, type);
    groupPrompt.setGroupId(groupId);
    groupPrompt.setType(type);
    groupPrompt.setContent(content);
    return this.saveOrUpdate(groupPrompt);
  }
}
