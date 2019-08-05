package com.chm.kd.kq.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chm.kd.kq.entity.GroupAdmin;
import org.apache.ibatis.annotations.Param;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupAdminMapper
 * @package com.chm.kd.kq.mapper
 * @since 2019-07-18
 * description 群管理控制表 Mapper 接口
 **/
public interface GroupAdminMapper extends BaseMapper<GroupAdmin> {

  /**
   * 获取type
   *
   * @param groupId
   * @param userId
   * @return
   */
  String getType(@Param("groupId") Long groupId, @Param("userId") Long userId);
}
