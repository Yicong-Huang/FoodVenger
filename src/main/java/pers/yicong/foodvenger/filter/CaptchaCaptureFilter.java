//package pers.yicong.foodvenger.filter;
//
//import org.apache.log4j.Logger;
//import org.hibernate.annotations.Filter;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * Filter for capturing Captcha fields.
// * It's purpose is to store these values internally
// */
//
//@Component
//@Filter(name = "captchaCaptureFilter")
//public class CaptchaCaptureFilter extends OncePerRequestFilter {
//
//    protected Logger logger = Logger.getLogger("filter");
//    private String recaptcha_response;
//    private String recaptcha_challenge;
//    private String remoteAddr;
//
//    @Override
//    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
//                                 FilterChain chain) throws IOException, ServletException {
//
//
//        chain.doFilter(req, res);
//    }
//
//    public String getRecaptcha_response() {
//        return recaptcha_response;
//    }
//
//
//}
