package com.chm.kd.kq.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chm.kd.kq.entity.GroupRemind;
import com.chm.kd.kq.mapper.GroupRemindMapper;
import com.chm.kd.kq.service.GroupRemindService;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupRemindServiceImpl
 * @package com.chm.kd.kq.service.impl
 * @since 2019-07-30
 * description 群提醒配置表 服务实现类
 **/
@Service
public class GroupRemindServiceImpl extends ServiceImpl<GroupRemindMapper, GroupRemind> implements GroupRemindService {

  @Override
  public GroupRemind getByGroupIdAndUserId(Long groupId, Long userId) {
    /**
     * select *
     * from tbl_group_remind
     * where GROUP_ID = 629751032
     *   and USER_ID = 985844208
     *   and ((START_TIME >= END_TIME and now() not between END_TIME and START_TIME) or
     *        (START_TIME < END_TIME and now() between START_TIME and END_TIME))
     *   and now() - LAST_REMIND_TIME > `INTERVAL`;
     */
    return this.getOne(Wrappers.<GroupRemind>lambdaQuery().eq(GroupRemind::getGroupId, groupId).eq(GroupRemind::getUserId, userId)
            .and(i -> i.nested(nested -> nested.apply("START_TIME >= END_TIME").apply("now() not between END_TIME and START_TIME")).or().
                    nested(nested -> nested.apply("START_TIME < END_TIME").apply("now() between START_TIME and END_TIME")))
            .apply("now() - LAST_REMIND_TIME > `INTERVAL`"), false);
  }
}
