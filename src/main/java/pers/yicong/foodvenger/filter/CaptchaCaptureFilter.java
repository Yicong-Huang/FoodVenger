package pers.yicong.foodvenger.filter;

import org.apache.log4j.Logger;
import org.hibernate.annotations.Filter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Filter for capturing Captcha fields.
 * It's purpose is to store these values internally
 */

@Component
@Filter(name = "captchaCaptureFilter")
public class CaptchaCaptureFilter extends OncePerRequestFilter {

    protected Logger logger = Logger.getLogger("filter");
    private String recaptcha_response;
    private String recaptcha_challenge;
    private String remoteAddr;

    @Override
    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                 FilterChain chain) throws IOException, ServletException {

        System.out.println(("Captcha capture filter"));
        // Assign values only when user has submitted a Captcha value.
        // Without this condition the values will be reset due to redirection
        // and CaptchaVerifierFilter will enter an infinite loop
        if (req.getParameter("recaptcha_response_field") != null) {
            recaptcha_response = req.getParameter("recaptcha_response_field");
            recaptcha_challenge = req.getParameter("recaptcha_challenge_field");
            remoteAddr = req.getRemoteAddr();
        }

        System.out.println(("challenge: " + recaptcha_challenge));


        System.out.println(("response: " + recaptcha_response));
        System.out.println(("remoteAddr: " + remoteAddr));

        // Proceed with the remaining filters
        chain.doFilter(req, res);
    }

    public String getRecaptcha_response() {
        return recaptcha_response;
    }

    public void setRecaptcha_response(String recaptchaResponse) {
        recaptcha_response = recaptchaResponse;
    }

    public String getRecaptcha_challenge() {
        return recaptcha_challenge;
    }

    public void setRecaptcha_challenge(String recaptchaChallenge) {
        recaptcha_challenge = recaptchaChallenge;
    }

    public String getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(String remoteAddr) {
        this.remoteAddr = remoteAddr;
    }
}
