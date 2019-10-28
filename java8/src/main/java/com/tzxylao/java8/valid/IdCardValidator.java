package com.tzxylao.java8.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author laoliangliang
 * @date 2019/10/22 15:55
 */
public class IdCardValidator implements ConstraintValidator<IdCardValid, Object> {

    private Pattern pattern = Pattern.compile("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])" +
            "\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$");
    @Override
    public void initialize(IdCardValid idCardValid) {
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Matcher matcher = pattern.matcher(o.toString());
        return matcher.matches();
    }
}
