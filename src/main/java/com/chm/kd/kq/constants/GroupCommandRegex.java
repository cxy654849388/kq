package com.chm.kd.kq.constants;

/**
 * @author caihongming
 * @version v1.0
 * @title GroupCommandRegex
 * @package com.chm.kd.kq.constants
 * @since 2019-07-26
 * description 群组命令正则表达式常量
 **/
public interface GroupCommandRegex {

  /**
   * 精确问命令
   */
  String EXACT_QUESTION = "^精确问 ([\\s\\S]+) 答 ([\\s\\S]+)$";

  /**
   * 模糊问命令
   */
  String FUZZY_QUESTION = "^模糊问 ([\\s\\S]+) 答 ([\\s\\S]+)$";

  /**
   * 复制问答命令
   */
  String COPY_QUESTION = "^复制问答 ([\\d，]+)$";

  /**
   * 模糊问命令
   */
  String FIND_QUESTION = "^查问 ([\\s\\S]+)$";

  /**
   * 删答命令
   */
  String DELETE_QUESTION = "^删答 ([\\d，]+)$";

}
