package com.chm.kd.kq.aop;

import com.chm.kd.kq.annotations.Role;
import com.chm.kd.kq.config.ApplicationConfig;
import com.chm.kd.kq.event.GroupMsg;
import com.chm.kd.kq.exception.EndException;
import com.chm.kd.kq.service.GroupAdminService;
import com.chm.kd.kq.utils.MapSetUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Stream;

/**
 * @author caihongming
 * @version v1.0
 * @title RoleAop
 * @package com.chm.kd.kq.aop
 * @since 2019-07-17
 * description 角色权限控制
 **/
@Aspect
@Component
@Slf4j
@Order(1)
public class RoleAop {

  @Autowired
  private ApplicationConfig applicationConfig;

  @Autowired
  private GroupAdminService groupAdminService;

  @Around(value = "@annotation(roles)&&args(groupMsg)")
  public Object role(ProceedingJoinPoint pjp, Role roles, GroupMsg groupMsg) throws Throwable {
    Long groupId = groupMsg.getGroupId();
    Long userId = groupMsg.getUserId();
    String role = getRole(groupId, userId);
    if (NumberUtils.compare(applicationConfig.getMaster(), userId) == 0) {
      // master有绝对权限
      pjp.proceed();
    } else if (Stream.of(roles.roles()).anyMatch(s -> StringUtils.equals(s, role))) {
      // 符合身份，可执行方法
      pjp.proceed();
    }
    return ObjectUtils.NULL;
  }

  /**
   * 获取成员信息
   *
   * @param groupId
   * @param userId
   * @return
   */
  private String getRole(Long groupId, Long userId) {
    Map<Long, String> groupAdminsMap = MapSetUtils.GROUP_ADMINS_MAP.get(groupId);
    String role = MapUtils.getString(groupAdminsMap, userId, "member");
    if (com.chm.kd.kq.enums.Role.owner.getCode().equals(role)) {
      return role;
    }
    return StringUtils.defaultIfBlank(groupAdminService.getRole(groupId, userId), role);
  }
}
