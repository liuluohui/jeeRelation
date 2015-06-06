import com.yonyou.bpm.scrt.ApplicationToken;
import com.yonyou.bpm.scrt.TokenGenerator;
import com.yonyou.bpm.scrt.client.ApplicationClientToken;
import com.yonyou.bpm.scrt.client.ApplicationRequestSigner;
import com.yonyou.bpm.scrt.client.ApplicationTokenLoader;
import com.yonyou.bpm.scrt.server.ApplicationRequestValidator;
import com.yonyou.bpm.scrt.server.ApplicationTokenGenerator;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

public class ApplicationAuthenticationTest {

	@Test
	public void testAuthentication() throws SecurityException, IOException {
		
		String appcode = UUID.randomUUID().toString();
		//定义足够长的数据，使其超�? 1024�? RSA 密钥允许的一次加密的数据长度(117)，检验是否仍然能够得到正确处理；
		String appid = UUID.randomUUID().toString() + UUID.randomUUID().toString();
		
		//生成 Token;
		TokenGenerator tokenGenerator = new ApplicationTokenGenerator();
		ApplicationToken token = tokenGenerator.createToken(appcode, appid);
		
		
		//模拟客户端解析令牌，对请求进行签名；
		String clientType = "BPM-SDK-JAVA-HG";
		String requestPath = "/service/runtime/tasks/" + UUID.randomUUID().toString();
		ApplicationClientToken clientToken = ApplicationTokenLoader.resolve(token.clientToken());
		ApplicationRequestSigner signer = new ApplicationRequestSigner(clientToken);
		String sign = signer.sign(clientType, requestPath);
		
		//模拟服务端验证签名；
		ApplicationRequestValidator validator = new ApplicationRequestValidator(appcode, token.serverCredential());
		boolean passed = validator.verify(clientType, requestPath, sign);
		
		assertTrue(passed);
	}

}
