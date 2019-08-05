package com.chm.kd.kq.utils;

import com.chm.kd.kq.info.GroupMemberInfo;
import com.google.common.collect.Maps;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author caihongming
 * @version v1.0
 * @title MapSetUtils
 * @package com.chm.kd.kq.utils
 * @since 2019-07-15
 * description
 **/
public class MapSetUtils {

  public static final Map<Long, Map<Long, String>> GROUP_ADMINS_MAP = Maps.newConcurrentMap();

  public static final Map<Long, LocalDateTime> REMIND_MAP = Maps.newConcurrentMap();

  public static final Map<String, Map<String, Object>> REPEAT_SEND_MAP = Maps.newConcurrentMap();

  public static final Map<Long, Map<Long, GroupMemberInfo>> GROUP_MEMBER_INFO_LIST_MAP = Maps.newConcurrentMap();
}
