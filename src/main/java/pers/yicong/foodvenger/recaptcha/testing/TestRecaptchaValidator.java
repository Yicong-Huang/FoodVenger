package pers.yicong.foodvenger.recaptcha.testing;

import pers.yicong.foodvenger.recaptcha.RecaptchaProperties;
import pers.yicong.foodvenger.recaptcha.validation.ErrorCode;
import pers.yicong.foodvenger.recaptcha.validation.RecaptchaValidator;
import pers.yicong.foodvenger.recaptcha.validation.ValidationResult;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class TestRecaptchaValidator extends RecaptchaValidator {

    protected final RecaptchaProperties.Testing testing;

    public TestRecaptchaValidator(RecaptchaProperties recaptcha) {
        super(null, recaptcha);
        testing = recaptcha.getTesting();
    }

    @Override
    public ValidationResult validate(HttpServletRequest request) {
        return getValidationResult();
    }

    @Override
    public ValidationResult validate(HttpServletRequest request, String ipAddress) {
        return getValidationResult();
    }

    @Override
    public ValidationResult validate(String userResponse) {
        return getValidationResult();
    }

    @Override
    public ValidationResult validate(String userResponse, String ipAddress) {
        return getValidationResult();
    }

    private ValidationResult getValidationResult() {
        if (testing.isSuccessResult()) {
            return new ValidationResult(true, new ArrayList<ErrorCode>());
        }
        return new ValidationResult(false, testing.getResultErrorCodes());
    }
}
