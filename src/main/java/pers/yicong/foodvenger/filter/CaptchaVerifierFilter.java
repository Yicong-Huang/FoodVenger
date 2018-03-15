//package pers.yicong.foodvenger.filter;
//
//
//import net.tanesha.recaptcha.ReCaptchaImpl;
//import net.tanesha.recaptcha.ReCaptchaResponse;
//import org.apache.log4j.Logger;
//import org.hibernate.annotations.Filter;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Properties;
//
///**
// * Filter for verifying if the submitted Captcha fields
// * are valid.
// * <p>
// * This filter also allows you to set a proxy if needed
// */
//@Component
//@Filter(name = "captchaVerifierFilter")
//public class CaptchaVerifierFilter extends OncePerRequestFilter {
//
//    protected Logger logger = Logger.getLogger("filter");
//    private Boolean useProxy = false;
//    private String proxyPort;
//    private String proxyHost;
//    private String failureUrl;
//    private String recaptcha_response;
//    private String recaptcha_challenge;
//    private String remoteAddr;
//    private String privateKey;
//
//    // Inspired by log output: AbstractAuthenticationProcessingFilter.java:unsuccessfulAuthentication:320)
//    // Delegating to authentication failure handlerorg.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler@15d4273
//    private SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
//
//    @Override
//    public void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
//                                 FilterChain chain) throws IOException, ServletException {
//
////        System.out.println("Captcha verifier filter");
////        if (req.getParameter("recaptcha_response_field") != null) {
////            System.out.println("has field");
//
//        recaptcha_response = req.getParameter("g-recaptcha-response");
////        recaptcha_challenge = req.getParameter("recaptcha-challenge-field");
//        remoteAddr = req.getRemoteAddr();
////        }
////        System.out.println("challenge: " + recaptcha_challenge);
//        System.out.println("response: " + recaptcha_response);
//        System.out.println("remoteAddr: " + remoteAddr);
//
//        // Assign values only when user has submitted a Captcha value
//        if (recaptcha_response != null) {
//
//            // Create a new recaptcha (by Soren Davidsen)
//            ReCaptchaImpl reCaptcha = new ReCaptchaImpl();
//
//            // Set the private key (assigned by Google)
//            reCaptcha.setPrivateKey(privateKey);
//
//            // Assign proxy if needed
//            if (useProxy) {
//                Properties systemSettings = System.getProperties();
//                systemSettings.put("http.proxyPort", proxyPort);
//                systemSettings.put("http.proxyHost", proxyHost);
//            }
//
//            // Send HTTP request to validate user's Captcha
//            ReCaptchaResponse reCaptchaResponse = reCaptcha.checkAnswer(remoteAddr, recaptcha_challenge, recaptcha_response);
//
//            // Check if valid
//            if (!reCaptchaResponse.isValid()) {
//                System.out.println("Captcha is invalid!");
//
//                // Redirect user to login page
//                failureHandler.setDefaultFailureUrl(failureUrl);
//                failureHandler.onAuthenticationFailure(req, res, new BadCredentialsException("Captcha invalid!"));
//
//            } else {
//                System.out.println("Captcha is valid!");
//            }
//
//            // Reset Captcha fields after processing
//            // If this method is skipped, everytime we access a page
//            // CaptchaVerifierFilter will infinitely send a request to the Google Captcha service!
//            resetCaptchaFields();
//        }
//
//        // Proceed with the remaining filters
//        chain.doFilter(req, res);
//    }
//
//    /**
//     * Reset Captcha fields
//     */
//    public void resetCaptchaFields() {
//        recaptcha_challenge = null;
//        recaptcha_response = null;
//        remoteAddr = null;
//    }
//
//    public Boolean getUseProxy() {
//        return useProxy;
//    }
//
//    public void setUseProxy(Boolean useProxy) {
//        this.useProxy = useProxy;
//    }
//
//    public String getProxyPort() {
//        return proxyPort;
//    }
//
//    public void setProxyPort(String proxyPort) {
//        this.proxyPort = proxyPort;
//    }
//
//    public String getProxyHost() {
//        return proxyHost;
//    }
//
//    public void setProxyHost(String proxyHost) {
//        this.proxyHost = proxyHost;
//    }
//
//    public String getFailureUrl() {
//        return failureUrl;
//    }
//
//    public void setFailureUrl(String failureUrl) {
//        this.failureUrl = failureUrl;
//    }
//
//
//    public String getPrivateKey() {
//        return privateKey;
//    }
//
//    public void setPrivateKey(String privateKey) {
//        this.privateKey = privateKey;
//    }
//}
