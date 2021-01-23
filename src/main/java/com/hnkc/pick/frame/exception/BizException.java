package com.hnkc.pick.frame.exception;

/**
 * 自定义业务异常
 * @author zhangguihua
 * @date 2020/09/29
 */
public class BizException extends RuntimeException {

  public BizException() {
    super();
  }

  public BizException(String message) {
    super(message);
  }

  private static final long serialVersionUID = 1L;
  
}