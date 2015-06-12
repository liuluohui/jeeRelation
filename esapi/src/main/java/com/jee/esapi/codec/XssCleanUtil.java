package com.jee.esapi.codec;

import org.apache.log4j.Logger;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.Policy;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;


/**
 * Created by Administrator on 2015/6/11.
 */
public final class XssCleanUtil {

    private static final Logger logger = Logger.getLogger(XssCleanUtil.class);

    private static final AntiSamy ANTI_SAMY = new AntiSamy();

    private static Policy policy;

    private static XssCleanUtil instance;

    private XssCleanUtil() {
    }

    /**
     * 单例模式
     *
     * @return
     */
    public static XssCleanUtil getInstance() {
        if (instance == null) {
            synchronized (XssCleanUtil.class) {
                if (instance == null) {
                    instance = new XssCleanUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 加载配置文件
     *
     * @param path
     * @throws PolicyException
     */
    public void loadPolicy(String path) throws PolicyException {
        if (policy == null) {
            synchronized (XssCleanUtil.class) {
                if (policy == null) {
                    policy = Policy.getInstance(XssCleanUtil.class.getClassLoader().getResourceAsStream(path));
                }
            }
        }
    }


    public String cleanXss(String param) {
        try {
            return ANTI_SAMY.scan(param, policy).getCleanHTML();
        } catch (ScanException e) {
            logger.error("Fail to scan input [" + param + "]", e);
        } catch (PolicyException e) {
            logger.error("Fail to clean input [" + param + "], looks like policy error!", e);
        }
        return param;
    }

}
