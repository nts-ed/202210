package com.northsea.result;

import com.northsea.utils.CheckException;
import com.northsea.utils.NoPermissionException;
import com.northsea.utils.UnloginException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @Author BenSitu
 * @CreateDate 2022/10/15
 * @Description
 */
@Slf4j
public abstract class ControllerAOP {
    protected abstract void targetMethod();

    @Around("targetMethod()")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) {
        long startTime = System.currentTimeMillis();

        try {
            Object result = (ResultBean<?>) pjp.proceed();

            // 如果需要打印入参，可以从这里取出打印
            // Object[] args = pjp.getArgs();

            // 本次操作用时（毫秒）
            long elapsedTime = System.currentTimeMillis() - startTime;
            log.info("[{}]use time: {}", pjp.getSignature(), elapsedTime);

            return result;
        } catch (Throwable e) {
            return handlerException(pjp, e);
        }
    }

    private ErrorMsg handlerException(ProceedingJoinPoint pjp, Throwable e) {
        ErrorMsg result = this.createResult();

        // 已知异常【注意：已知异常不要打印堆栈，否则会干扰日志】
        // 校验出错，参数非法
        if (e instanceof CheckException || e instanceof IllegalArgumentException) {
            result.setMsg(e.getLocalizedMessage());
            result.setCode(ResultBean.CHECK_FAIL);
        }
        // 没有登陆
        else if (e instanceof UnloginException) {
            result.setMsg("Unlogin");
            result.setCode(ResultBean.NO_LOGIN);
        }
        // 没有权限
        else if (e instanceof NoPermissionException) {
            result.setMsg("NO PERMISSION");
            result.setCode(ResultBean.NO_PERMISSION);
        } else {
            log.error(pjp.getSignature() + " error ", e);

            // TODO 未知的异常，应该格外注意，可以发送邮件通知等
            //
            result.setMsg(e.toString());
            result.setCode(ResultBean.UNKNOWN_EXCEPTION);
        }

        return result;
    }

    protected abstract ErrorMsg createResult();
}
