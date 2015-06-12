package com.jee.esapi.listener;

import com.jee.esapi.codec.XssCleanUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.owasp.validator.html.PolicyException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Administrator on 2015/6/11.
 */
public class XssListener implements ServletContextListener {


    private static final Logger logger = Logger.getLogger(XssListener.class);

    public void contextInitialized(ServletContextEvent event) {
        String antisamyConfigPath = event.getServletContext().getInitParameter("antisamy.config.path");
        if (StringUtils.isEmpty(antisamyConfigPath)) {
            logger.info("Init Antisamy Policy,XssFilter initParam [antisamy.config.path] is not config,use classpath [antisamy.xml].");
            antisamyConfigPath = "antisamy.xml";
        } else {
            logger.info("Init Antisamy Policy use XssFilter initParam [antisamy.config.path = " + antisamyConfigPath + "]");
        }
        try {
            XssCleanUtil.getInstance().loadPolicy(antisamyConfigPath);
        } catch (PolicyException e) {
            throw new RuntimeException("antisamy configration [" + antisamyConfigPath + "] is not found", e);
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
