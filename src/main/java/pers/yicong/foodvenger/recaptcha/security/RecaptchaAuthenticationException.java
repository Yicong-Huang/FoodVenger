package pers.yicong.foodvenger.recaptcha.security;

import org.springframework.security.core.AuthenticationException;
import pers.yicong.foodvenger.recaptcha.validation.ErrorCode;

import java.util.List;

import static java.util.Collections.unmodifiableList;

public class RecaptchaAuthenticationException extends AuthenticationException {

    private final List<ErrorCode> errorCodes;

    public RecaptchaAuthenticationException(List<ErrorCode> errorCodes) {
        super("reCAPTCHA authentication error: " + errorCodes);
        this.errorCodes = errorCodes;
    }

    public List<ErrorCode> getErrorCodes() {
        return unmodifiableList(errorCodes);
    }
}
