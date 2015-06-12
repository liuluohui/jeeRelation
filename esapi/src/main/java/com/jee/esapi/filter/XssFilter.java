package com.jee.esapi.filter;

import com.jee.esapi.codec.XssCleanUtil;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2015/6/11.
 */
public class XssFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String, String[]> paramMap = servletRequest.getParameterMap();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String[] valueTable = entry.getValue();
            for (int i = 0; i < valueTable.length; i++) {
                valueTable[i] = XssCleanUtil.getInstance().cleanXss(valueTable[i]);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }

}
