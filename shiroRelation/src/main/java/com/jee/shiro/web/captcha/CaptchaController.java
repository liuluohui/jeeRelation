package com.jee.shiro.web.captcha;

import com.octo.captcha.service.CaptchaService;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Administrator on 2015/8/1.
 */
@Controller
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @RequestMapping("/captcha")
    public void genCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String captchaId = request.getSession().getId();
        BufferedImage challenge =
                (BufferedImage) captchaService.getChallengeForID(captchaId,
                        request.getLocale());


        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中。
        OutputStream sos = null;
        try {
            sos = response.getOutputStream();
            ImageIO.write(challenge, "jpeg", sos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(sos);
        }
    }
}
