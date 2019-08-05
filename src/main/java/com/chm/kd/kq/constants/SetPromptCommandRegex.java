package com.chm.kd.kq.constants;

/**
 * @author caihongming
 * @version v1.0
 * @title SetPromptCommandRegex
 * @package com.chm.kd.kq.constants
 * @since 2019-07-26
 * description
 **/
public interface SetPromptCommandRegex {

  /**
   * 设置提示
   */
  String SET_GROUP_PROMPT = "设置提示 (.+) ([\\s\\S]+)";

  /**
   * 查看提示
   */
  String SET_GROUP_PROMPT_VIEW = "设置提示 (.+) 查看";

  /**
   * 删除提示
   */
  String SET_GROUP_PROMPT_DELETE = "设置提示 (.+) 删除";

  /**
   * 提示语设置命令
   */
  String SET_PROMPT_COMMAND = "设置提示 ([^ ]+) ([\\s\\S]+)";
}
