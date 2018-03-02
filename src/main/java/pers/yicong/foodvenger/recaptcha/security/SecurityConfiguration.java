package pers.yicong.foodvenger.recaptcha.security;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import pers.yicong.foodvenger.recaptcha.RecaptchaProperties;
import pers.yicong.foodvenger.recaptcha.security.login.*;
import pers.yicong.foodvenger.recaptcha.validation.RecaptchaValidator;

@Configuration("recaptchaSecurityConfiguration")
@ConditionalOnClass({EnableWebSecurity.class, AbstractAuthenticationProcessingFilter.class})
@EnableConfigurationProperties(RecaptchaProperties.class)
public class SecurityConfiguration {

    private final RecaptchaProperties recaptcha;

    public SecurityConfiguration(RecaptchaProperties recaptcha) {
        this.recaptcha = recaptcha;
    }

    @Bean
    @ConditionalOnMissingBean
    public FormLoginConfigurerEnhancer formLoginConfigurerEnhancer(LoginFailuresClearingHandler successHandler, LoginFailuresCountingHandler failureHandler, RecaptchaValidator recaptchaValidator, LoginFailuresManager failuresManager) {
        RecaptchaAuthenticationFilter authenticationFilter = new RecaptchaAuthenticationFilter(recaptchaValidator, recaptcha, failuresManager);
        return new FormLoginConfigurerEnhancer(authenticationFilter, successHandler, failureHandler);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFailuresManager loginFailuresManager() {
        return new InMemoryLoginFailuresManager(recaptcha);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFailuresCountingHandler loginFailuresCountingHandler(LoginFailuresManager failuresManager, RecaptchaAwareRedirectStrategy redirectStrategy) {
        return new LoginFailuresCountingHandler(failuresManager, recaptcha, redirectStrategy);
    }

    @Bean
    @ConditionalOnMissingBean
    public LoginFailuresClearingHandler loginFailuresClearingHandler(LoginFailuresManager failuresManager) {
        return new LoginFailuresClearingHandler(failuresManager);
    }

    @Bean
    @ConditionalOnMissingBean
    public RecaptchaAwareRedirectStrategy recaptchaAwareRedirectStrategy(LoginFailuresManager failuresManager) {
        return new RecaptchaAwareRedirectStrategy(failuresManager);
    }
}
