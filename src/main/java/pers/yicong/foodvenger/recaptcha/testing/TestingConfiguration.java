package pers.yicong.foodvenger.recaptcha.testing;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pers.yicong.foodvenger.recaptcha.RecaptchaProperties;
import pers.yicong.foodvenger.recaptcha.validation.RecaptchaValidator;

@Configuration("recaptchaTestingConfiguration")
@EnableConfigurationProperties(RecaptchaProperties.class)
@ConditionalOnProperty(name = "recaptcha.testing.enabled")
public class TestingConfiguration {

    private final RecaptchaProperties recaptcha;

    public TestingConfiguration(RecaptchaProperties recaptcha) {
        this.recaptcha = recaptcha;
    }

    @Bean
    @ConditionalOnMissingBean
    public RecaptchaValidator userResponseValidator() {
        return new TestRecaptchaValidator(recaptcha);
    }
}
