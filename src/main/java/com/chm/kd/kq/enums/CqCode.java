package com.chm.kd.kq.enums;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author caihongming
 * @version v1.0
 * @title CqCode
 * @package com.chm.kd.kq.enums
 * @since 2019-07-16
 * description
 **/
public enum CqCode {

  /**
   * 系统表情
   * {1}为≥0的数字
   * 举例：[CQ:face,id=14]（发送一个微笑的系统表情）
   */
  face("[CQ:face,id={1}]", "系统表情"),

  /**
   * emoji表情
   * {1}为emoji字符的unicode编号
   * 举例：[CQ:emoji,id=128513]（发送一个大笑的emoji表情）
   */
  emoji("[CQ:emoji,id={1}]", "emoji表情"),

  /**
   * 原创表情
   * {1}为该原创表情的ID，存放在酷Q目录的data\bface\下
   */
  bface("[CQ:bface,id={1}]", "原创表情"),

  /**
   * 小表情
   * {1}为该小表情的ID
   */
  sface("[CQ:sface,id={1}]", "小表情"),

  /**
   * 发送自定义图片
   * {1}为图片文件名称，图片存放在酷Q目录的data\image\下
   * 举例：[CQ:image,file=1.jpg]（发送data\image\1.jpg）
   */
  image("[CQ:image,file={1}]", "发送自定义图片"),

  /**
   * 发送语音
   * 1}为音频文件名称，音频存放在酷Q目录的data\record\下
   * {2}为是否为变声，若该参数为true则显示变声标记。该参数可被忽略。
   * 举例：[CQ:record,file=1.silk，magic=true]（发送data\record\1.silk，并标记为变声）
   */
  record("[CQ:record,file={1},magic={2}]", "发送语音"),

  /**
   * @某人 {1}为被@的群成员帐号。若该参数为all，则@全体成员（次数用尽或权限不足则会转换为文本）。
   * 举例：[CQ:at,qq=123456]
   */
  at("[CQ:at,qq={1}]", "@某人"),

  /**
   * 发送猜拳魔法表情
   * {1}为猜拳结果的类型，暂不支持发送时自定义。该参数可被忽略。
   * 1 - 猜拳结果为石头
   * 2 - 猜拳结果为剪刀
   * 3 - 猜拳结果为布
   */
  rps("[CQ:rps,type={1}]", "发送猜拳魔法表情"),

  /**
   * 发送掷骰子魔法表情
   * {1}对应掷出的点数，暂不支持发送时自定义。该参数可被忽略。
   */
  dice("[CQ:dice,type={1}]", "发送掷骰子魔法表情"),

  /**
   * 戳一戳（原窗口抖动，仅支持好友消息使用）
   */
  shake("[CQ:shake]", "戳一戳（原窗口抖动，仅支持好友消息使用）"),

  /**
   * 匿名发消息（仅支持群消息使用）
   * 本CQ码需加在消息的开头。
   * 当{1}为true时，代表不强制使用匿名，如果匿名失败将转为普通消息发送。
   * 当{1}为false或ignore参数被忽略时，代表强制使用匿名，如果匿名失败将取消该消息的发送。
   * 举例：
   * [CQ:anonymous,ignore=true]
   * [CQ:anonymous]
   */
  anonymous("[CQ:anonymous,ignore={1}]", "匿名发消息（仅支持群消息使用）"),

  /**
   * 发送音乐
   * {1}为音乐平台类型，目前支持qq、163、xiami
   * {2}为对应音乐平台的数字音乐id
   * 注意：音乐只能作为单独的一条消息发送
   * 举例：
   * [CQ:music,type=qq,id=422594]（发送一首QQ音乐的“Time after time”歌曲到群内）
   * [CQ:music,type=163,id=28406557]（发送一首网易云音乐的“桜咲く”歌曲到群内）
   */
  music("[CQ:music,type={1},id={2}]", "发送音乐"),

  /**
   * 发送音乐自定义分享
   * {1}为分享链接，即点击分享后进入的音乐页面（如歌曲介绍页）。
   * {2}为音频链接（如mp3链接）。
   * {3}为音乐的标题，建议12字以内。
   * {4}为音乐的简介，建议30字以内。该参数可被忽略。
   * {5}为音乐的封面图片链接。若参数为空或被忽略，则显示默认图片。
   * 注意：音乐自定义分享只能作为单独的一条消息发送
   */
  music_customize("[CQ:music,type=custom,url={1},audio={2},title={3},content={4},image={5}]", "发送音乐自定义分享"),

  /**
   * 发送链接分享
   * {1}为分享链接。
   * {2}为分享的标题，建议12字以内。
   * {3}为分享的简介，建议30字以内。该参数可被忽略。
   * {4}为分享的图片链接。若参数为空或被忽略，则显示默认图片。
   * 注意：链接分享只能作为单独的一条消息发送
   */
  share("[CQ:share,url={1},title={2},content={3},image={4}]", "发送链接分享"),

  /**
   * 默认
   */
  default_("default", "默认");

  private String code;

  private String description;

  CqCode(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * 根据value返回枚举类型,主要在switch中使用
   *
   * @param code
   * @return
   */
  public static CqCode getByCode(String code) {
    for (CqCode cqCode : values()) {
      if (cqCode.getCode().equals(code)) {
        return cqCode;
      }
    }
    return default_;
  }

  public String getCqCode(String... args) {
    if (ArrayUtils.isNotEmpty(args)) {
      String code = this.getCode();
      for (int i = 0; i < args.length; i++) {
        code = StringUtils.replace(code, String.format("{%d}", i + 1), args[i]);
      }
      return code;
    }
    return "";
  }
}
