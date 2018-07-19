package com.admxj.spring.SpringDemo.interceptor;

import com.admxj.spring.SpringDemo.dto.SimpleJsonResult;
import com.admxj.spring.SpringDemo.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

@Component
public class OneInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        logger.info("放行");

        logger.info(((HandlerMethod)handler).getBean().getClass().getName());
        logger.info(((HandlerMethod)handler).getMethod().getName());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
    }

    public void returnErrorResponse(HttpServletRequest request, HttpServletResponse response, SimpleJsonResult result)
            throws IOException, UnsupportedEncodingException{
        OutputStream out = null;

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/json");
            out = response.getOutputStream();
            out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
            out.flush();
        }finally {
            if (out!=null)
                out.close();
        }
    }
}
