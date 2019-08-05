package com.chm.kd.kq.utils;

import com.chm.kd.kq.constants.GroupCommandRegex;

import java.text.ParseException;
import java.util.regex.Pattern;

/**
 * @author caihongming
 * @version v1.0
 * @title RegexUtils
 * @package com.chm.kd.kq.utils
 * @since 2019-07-15
 * description
 **/
public class RegexUtils {

  /**
   * 通用命令
   */
  public static final String COMMAND = "^([^ ]+) .+$";

  /**
   * 定时发送
   */
  public static final String TIMING_SEND = "^定时发送 ([\\s\\S]+) 时间 ([\\d]{4}-[\\d]{2}-[\\d]{2} [\\d]{2}:[\\d]{2}:[\\d]{2})+$";

  /**
   * 重复发送
   */
  public static final String REPEAT_SEND = "^重复发送 ([\\s\\S]+) 次数 ([1-9]\\d*) 间隔 ((((\\d{1,2})小时)|((\\d{1,2})分钟?)|((\\d{1,2})秒钟?))+)$";

  /**
   * 删 重复发送
   */
  public static final String DELETE_REPEAT_SEND = "^删重复发送 (.+)$";

  /**
   * 名片格式校验
   */
  public static final String BUSINESS_CARD = "^\\d{2}.?-[\\s\\S]+-[\\s\\S]+$";

  public static boolean matchRegex(String regex, String str) {
    if (null == regex) {
      return false;
    }
    return Pattern.matches(regex, str);
  }

  public static boolean notMatchRegex(String regex, String str) {
    return !matchRegex(regex, str);
  }

  public static String extractQuestion(String str) {
    if (null != str) {
      if (matchRegex(GroupCommandRegex.EXACT_QUESTION, str) || matchRegex(GroupCommandRegex.FUZZY_QUESTION, str)) {
        return str.replaceAll(GroupCommandRegex.EXACT_QUESTION, "$1");
      }
    }
    return "";
  }

  public static String extractAnswer(String str) {
    if (null != str) {
      if (matchRegex(GroupCommandRegex.EXACT_QUESTION, str) || matchRegex(GroupCommandRegex.FUZZY_QUESTION, str)) {
        return str.replaceAll(GroupCommandRegex.EXACT_QUESTION, "$2");
      }
    }
    return "";
  }

  public static void main(String[] args) throws ParseException {
    String str = "开启问答";
  }


}
