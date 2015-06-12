import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * Created by Administrator on 2015/5/29.
 */
public class RelamTutorial {

    public static void main(String[] args) {
        SecurityManager securityManager = new DefaultSecurityManager(new JdbcRealm());
        SecurityUtils.setSecurityManager(securityManager);


    }
}
