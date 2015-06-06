package com.jee.web.handler;

import com.jee.web.api.exception.RestfulException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2015/6/6.
 */
public class RestfulExceptionHandler implements HandlerExceptionResolver {

    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        if (e instanceof RestfulException) {

            //设置返回的http 状态码
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ModelAndView("restfulException", "errorMsg", e.getMessage());
        }
        return new ModelAndView("exception");
    }

}
