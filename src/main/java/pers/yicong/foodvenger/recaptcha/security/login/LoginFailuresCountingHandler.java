package pers.yicong.foodvenger.recaptcha.security.login;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import pers.yicong.foodvenger.recaptcha.RecaptchaProperties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFailuresCountingHandler extends SimpleUrlAuthenticationFailureHandler {

    protected final LoginFailuresManager failuresManager;

    public LoginFailuresCountingHandler(LoginFailuresManager failuresManager, RecaptchaProperties recaptcha, RecaptchaAwareRedirectStrategy redirectStrategy) {
        this.failuresManager = failuresManager;
        setDefaultFailureUrl(recaptcha.getSecurity().getFailureUrl());
        setRedirectStrategy(redirectStrategy);
    }

//    public LoginFailuresCountingHandler(LoginFailuresManager failuresManager, RecaptchaProperties recaptcha, RecaptchaAwareRedirectStrategy redirectStrategy) {
//    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        failuresManager.addLoginFailure(request);
        super.onAuthenticationFailure(request, response, exception);
    }

    @Override
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        if (!RecaptchaAwareRedirectStrategy.class.isAssignableFrom(redirectStrategy.getClass())) {
            throw new IllegalArgumentException("Invalid redirect strategy. Redirect strategy must be an instance of " + RecaptchaAwareRedirectStrategy.class.getName() + " but is " + redirectStrategy);
        }
        super.setRedirectStrategy(redirectStrategy);
    }
}
