package com.chm.kd.kq.runner;

import com.chm.kd.kq.api.GroupApi;
import com.chm.kd.kq.client.KqWebSocketClient;
import com.chm.kd.kq.entity.GroupSwitch;
import com.chm.kd.kq.entity.QuestionAnswer;
import com.chm.kd.kq.enums.Role;
import com.chm.kd.kq.info.GroupInfo;
import com.chm.kd.kq.info.GroupMemberInfo;
import com.chm.kd.kq.service.GroupSwitchService;
import com.chm.kd.kq.service.QuestionAnswerService;
import com.chm.kd.kq.utils.MapSetUtils;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * @author caihongming
 * @version v1.0
 * @title WebSocketRunner
 * @package com.chm.kd.kq.runner
 * @since 2019-07-11
 * description
 **/
@Component
@Slf4j
public class WebSocketRunner implements CommandLineRunner {

  private static String uri = "//127.0.0.1:6700";

  @Value("${cq.http-api.host}")
  private String host;

  @Value("${cq.http-api.port}")
  private String port;

  @Resource(name = "groupListener")
  private ExecutorService groupListener;

  @Autowired
  private GroupApi groupApi;

  @Autowired
  private QuestionAnswerService questionAnswerService;

  @Autowired
  private GroupSwitchService groupSwitchService;

  private void start() {
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      container.connectToServer(KqWebSocketClient.class, URI.create("ws://" + host + ":" + port));
    } catch (DeploymentException | IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  private void init() {
    initAdmins();
    initGroupConfig();
    MapSetUtils.REMIND_MAP.put(806875021L, LocalDateTime.now());
  }

  private void initAdmins() {
    List<GroupInfo> groupInfoList = groupApi.getGroupList();
    groupInfoList.forEach(groupInfo -> {
      List<GroupMemberInfo> groupMemberInfoList = groupApi.getGroupMemberList(groupInfo.getGroupId());
      Map<Long, String> groupAdminsMap = groupMemberInfoList.stream().filter(groupMemberInfo -> Role.isAdmin(groupMemberInfo.getRole()))
              .collect(Collectors.toConcurrentMap(GroupMemberInfo::getUserId, GroupMemberInfo::getRole));
      MapSetUtils.GROUP_ADMINS_MAP.put(groupInfo.getGroupId(), groupAdminsMap);
    });
  }

  private void initGroupConfig() {
    List<GroupInfo> groupInfoList = groupApi.getGroupList();
    groupInfoList.stream().filter(groupInfo -> !groupSwitchService.checkExistByGroupId(groupInfo.getGroupId())).forEach(groupInfo -> {
      GroupSwitch groupSwitch = new GroupSwitch();
      groupSwitch.setGroupId(groupInfo.getGroupId());
      groupSwitch.setMainSwitch(true);
      groupSwitch.setQuestionSwitch(false);
      groupSwitchService.save(groupSwitch);
    });
  }

  @Override
  public void run(String... args) throws Exception {
    start();
    init();
  }
}
