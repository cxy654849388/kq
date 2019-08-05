package com.chm.kd.kq.command.impl;

import com.chm.kd.kq.annotations.End;
import com.chm.kd.kq.annotations.Regex;
import com.chm.kd.kq.annotations.Role;
import com.chm.kd.kq.api.MsgApi;
import com.chm.kd.kq.command.SetGroupAdminCommand;
import com.chm.kd.kq.constants.SetGroupAdminCommandRegex;
import com.chm.kd.kq.entity.GroupAdmin;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.service.GroupAdminService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author caihongming
 * @version v1.0
 * @title SetGroupAdminCommandImpl
 * @package com.chm.kd.kq.command.impl
 * @since 2019-07-18
 * description
 **/
@Service
public class SetGroupAdminCommandImpl implements SetGroupAdminCommand {

  @Autowired
  private GroupAdminService groupAdminService;

  @Autowired
  private MsgApi msgApi;

  @Override
  @End
  @Role(roles = {"owner"})
  @Regex(anyMatchRegexps = {SetGroupAdminCommandRegex.SET_GROUP_ADMIN_VIEW})
  public void view(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    List<GroupAdmin> view = groupAdminService.view(groupId);
    StringBuffer stringBuffer = new StringBuffer();
    if (CollectionUtils.isEmpty(view)) {
      stringBuffer.append("本群暂无设置群管理员\n");
    } else {
      stringBuffer.append("共查看").append(view.size()).append("相关数据，详情如下所示：\n").append("--------------------\n");
      view.forEach(groupAdmin -> {
        stringBuffer.append("用户QQ：").append(groupAdmin.getUserId());
        if (StringUtils.equals(groupAdmin.getType(), "2")) {
          stringBuffer.append("（禁止）");
        }
        stringBuffer.append("\n");
      });
    }
    stringBuffer.append("--------------------\n")
            .append("发送“设置群管理员 增加 用户QQ”增加机器人管理员\n").append("例如：设置群管理员 增加 ").append(groupMsg.getSelfId()).append("（可用，分隔增加多个）\n")
            .append("发送“设置群管理员 删除 用户QQ”删除机器人管理员\n").append("例如：设置群管理员 删除 ").append(groupMsg.getSelfId()).append("（可用，分隔删除多个）\n")
            .append("发送“设置群管理员 禁止 用户QQ”删除机器人管理员\n").append("例如：设置群管理员 禁止 ").append(groupMsg.getSelfId()).append("（可用，分隔禁止多个）\n")
            .append("发送“设置群管理员 清空”删除机器人管理员");
    msgApi.sendGroupMsg(groupId, stringBuffer.toString());
  }

  @Override
  @End
  @Role(roles = {"owner"})
  @Regex(anyMatchRegexps = {SetGroupAdminCommandRegex.SET_GROUP_ADMIN_ADD})
  public void add(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String userIdStr = readMessage.replaceAll(SetGroupAdminCommandRegex.SET_GROUP_ADMIN_ADD, "$1");
    List<Long> userIdList = Stream.of(StringUtils.split(userIdStr, "，")).map(NumberUtils::createLong).collect(Collectors.toList());
    long count = groupAdminService.add(groupId, userIdList);
    msgApi.sendGroupMsg(groupId, String.format("操作成功，本次共增加机器人管理员数据%d条！", count));
  }

  @Override
  @End
  @Role(roles = {"owner"})
  @Regex(anyMatchRegexps = {SetGroupAdminCommandRegex.SET_GROUP_ADMIN_DELETE})
  public void delete(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String userIdStr = readMessage.replaceAll(SetGroupAdminCommandRegex.SET_GROUP_ADMIN_DELETE, "$1");
    List<Long> userIdList = Stream.of(StringUtils.split(userIdStr, "，")).map(NumberUtils::createLong).collect(Collectors.toList());
    long count = groupAdminService.delete(groupId, userIdList);
    msgApi.sendGroupMsg(groupId, String.format("操作成功，本次共删除机器人管理员数据%d条！", count));
  }

  @Override
  @End
  @Role(roles = {"owner"})
  @Regex(anyMatchRegexps = {SetGroupAdminCommandRegex.SET_GROUP_ADMIN_BAN})
  public void ban(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    String readMessage = groupMsg.getRawMessage();
    String userIdStr = readMessage.replaceAll(SetGroupAdminCommandRegex.SET_GROUP_ADMIN_BAN, "$1");
    List<Long> userIdList = Stream.of(StringUtils.split(userIdStr, "，")).map(NumberUtils::createLong).collect(Collectors.toList());
    long count = groupAdminService.ban(groupId, userIdList);
    msgApi.sendGroupMsg(groupId, String.format("操作成功，本次共禁止机器人管理员数据%d条！", count));
  }

  @Override
  @End
  @Role(roles = {"owner"})
  @Regex(anyMatchRegexps = {SetGroupAdminCommandRegex.SET_GROUP_ADMIN_EMPTY})
  public void empty(GroupMsg groupMsg) {
    Long groupId = groupMsg.getGroupId();
    long count = groupAdminService.empty(groupId);
    msgApi.sendGroupMsg(groupId, String.format("操作成功，本次共清空机器人管理员数据%d条！", count));
  }
}
