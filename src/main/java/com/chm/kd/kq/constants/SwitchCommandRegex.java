package com.chm.kd.kq.constants;

/**
 * @author caihongming
 * @version v1.0
 * @title SwitchCommandRegex
 * @package com.chm.kd.kq.constants
 * @since 2019-07-26
 * description 开关控制正则表达式常量
 **/
public interface SwitchCommandRegex {

  /**
   * 开关命令
   */
  String SWITCH_COMMAND = "^(开启|关闭)(.+)$";

  /**
   * 开启问答命令
   */
  String OPEN_QUESTION = "^开启问答$";

  /**
   * 关闭问答命令
   */
  String CLOSE_QUESTION = "^关闭问答$";

  /**
   * 开启加群提示
   */
  String OPEN_JOIN_GROUP = "^开启加群提示$";

  /**
   * 关闭加群提示
   */
  String CLOSE_JOIN_GROUP = "^关闭加群提示$";

  /**
   * 开启加群提示
   */
  String OPEN_QUIT_GROUP = "^开启退群提示$";

  /**
   * 关闭加群提示
   */
  String CLOSE_QUIT_GROUP = "^关闭退群提示$";

  /**
   * 开启改名提示
   */
  String OPEN_MODIFY_CARD = "^开启改名提示$";

  /**
   * 关闭改名提示
   */
  String CLOSE_MODIFY_CARD = "^关闭改名提示$";
}
