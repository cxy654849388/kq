package com.chm.kd.kq.exception;

/**
 * @author caihongming
 * @version v1.0
 * @title 完成
 * @package com.chm.kd.kq.exception
 * @since 2019-07-17
 * description 用于程序执行完毕跳出
 **/
public class EndException extends RuntimeException {

  private static EndException endException = new EndException();

  private EndException() {
  }

  public static EndException getInstance() {
    return endException;
  }

}
