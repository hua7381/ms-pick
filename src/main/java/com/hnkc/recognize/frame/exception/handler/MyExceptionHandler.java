package com.hnkc.recognize.frame.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hnkc.recognize.frame.exception.BizException;
import com.hnkc.recognize.frame.tool.LogTool;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理
 * @author zhangguihua
 * @date 2020/09/29
 */
@ControllerAdvice
public class MyExceptionHandler {
    Logger logger = LoggerFactory.getLogger(MyExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(HttpServletRequest req, HttpServletResponse resp, Exception ex) {

        String message = "";
        Integer status = null;

        if (ex instanceof HttpMessageNotReadableException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = "参数格式错误: " + ex.getMessage();

        } else if (ex instanceof IllegalArgumentException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = ex.getMessage();

        } else if (ex instanceof BizException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = ex.getMessage();

        // @Validated
        } else if (ex instanceof MethodArgumentNotValidException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = getMsg((MethodArgumentNotValidException)ex);

        // shiro
        } else if (ex instanceof AccountException) {
            status = HttpServletResponse.SC_BAD_REQUEST;
            message = ex.getMessage();
        } else if (ex instanceof UnauthorizedException) {
            status = HttpServletResponse.SC_FORBIDDEN;
            logger.warn("无访问权限: "+req.getRequestURI()+", msg: "+ex.getMessage());
            message = "无访问权限";

        } else if(ex instanceof HttpRequestMethodNotSupportedException) {
            status = HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            message = "请求方式不支持, url: "+req.getRequestURI()+", errMsg: "+ex.getMessage();
            logger.warn(message);

        // 404
        } else if (ex instanceof NoHandlerFoundException) {
            status = HttpServletResponse.SC_NOT_FOUND;
            message = "请求的地址不存在";

        } else if(ex instanceof HttpMessageNotReadableException && ex.getCause() != null && ex.getCause() instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException){
            status = HttpServletResponse.SC_BAD_REQUEST;
            String msg = ex.getMessage();
            if(msg != null) {
                Boolean contain = msg.indexOf(";") > -1;
                if(contain) {
                    msg = msg.substring(0, msg.indexOf(";"));
                }
            }
            message = "JSON参数格式错误:"+msg;

        } else {
            String exMsg = ex.getMessage();
            if(exMsg != null) {
                Boolean contain1 = exMsg.contains("ORA-12899");
                Boolean contain2 = exMsg.contains("ORA-00001");
                if(contain1) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                    message = "输入的某项内容过长";
                } else if(contain2) {
                    status = HttpServletResponse.SC_BAD_REQUEST;
                    message = "违反唯一约束条件";
                }
            }

            // 被包装的异常，取出原异常
            if (ex instanceof RuntimeException && ex.getCause() != null) {
                ex = (Exception) ex.getCause();
            }

            if(ex instanceof BindException) {
                status = HttpServletResponse.SC_BAD_REQUEST;
                message = "参数格式错误";
                logger.warn("参数格式错误： "+LogTool.simplifyTrace(ex));

            } else {
                status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
                message = "非常抱歉, 发生了故障, 请刷新或重新登录后重试, 若仍有问题, 请联系管理员解决";

                handleError(req, ex);
            }
        }
        resp.setStatus(status == null ? 500 : status);
        return message;
    }

    private void handleError(HttpServletRequest req, Exception ex) {
        try {
            StringBuffer sb = new StringBuffer();
            sb.append("内部错误\n");
            sb.append("method: "+req.getMethod()+"\n");
            sb.append("reques url: "+req.getRequestURL()+"\n");
            sb.append("stack: "+LogTool.simplifyTrace(ex));
            logger.error(sb.toString(), LogTool.props());
        } catch(Exception e) {
            logger.error("打印异常堆栈失败: "+e.getMessage());
        }
    }

    private String getMsg(MethodArgumentNotValidException ex) {
        StringBuffer sb = new StringBuffer();
        ex.getBindingResult().getFieldErrors().forEach(item -> {
            if(sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(item.getDefaultMessage());
        });
        return sb.toString();
    }

}