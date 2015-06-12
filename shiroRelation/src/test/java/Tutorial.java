import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/5/28.
 */
public class Tutorial {

    private static final Logger log = LoggerFactory.getLogger(Tutorial.class);

    public static void main(String[] args) {
        log.info("My first shiro Application");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("somekey", "value");

        if (!currentUser.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespa");
            token.setRememberMe(true);

            try {
                currentUser.login(token);
            } catch (UnknownAccountException e) {
                log.error("unkown username");
            } catch (IncorrectCredentialsException iae) {
                log.error("wrong password");
            } catch (LockedAccountException lae) {
                log.error("Account locked");
            } catch (AuthenticationException e) {
                log.error("other error");
            }

            log.info("User ["+currentUser.getPrincipal()+"] login successful");


            if(currentUser.hasRole("schwartz")){
                log.info("May has schwartz with you");
            }else{
                log.warn("hello ,mere schwartz");
            }

            if(currentUser.isPermitted("lightstarr:wild")){
                log.info("you may have a lightstar ring,use it wisely");
            }else{
                log.warn("Sorry,light star rings are for schwartz masters only");
            }

            if(currentUser.isPermitted("winnebago:drive:eagle5")){
                log.info("you are permitted to drive the winnebago with license plat (id) eagle5 .Here are the keys - have fun!");
            }else{
                log.info("Sorry,you aren't allowed to drive the eagle5 winnebago!");
            }

            currentUser.logout();
        }

        System.exit(0);


    }
}
