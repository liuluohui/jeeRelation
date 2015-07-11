package com.jee.shiro.filter;

import com.jee.shiro.entity.User;
import com.jee.shiro.service.account.AccountService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/7/11.
 */
public class UserSettingFilter extends AccessControlFilter {

    @Autowired
    private AccountService userService;

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (subject == null) {
            return false;
        }
        HttpSession session = ((HttpServletRequest) request).getSession();
        User current_user = (User) session.getAttribute("currentUser");
        //判断session是否失效，若失效刷新之
        if (current_user == null) {
            String username = (String) subject.getPrincipal();
            User user = userService.findUserByLoginName(username);
            session.setAttribute("currentUser", user);
        }
        return true;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return true;
    }

}
