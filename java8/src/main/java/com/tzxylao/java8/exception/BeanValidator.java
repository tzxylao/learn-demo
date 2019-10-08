package com.tzxylao.java8.exception;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.ValidationException;
import java.util.Set;

/**
 * Created by dhlzj on 2016/10/24.
 */
public class BeanValidator {

    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private static Validator validator = factory.getValidator();

    public static <T> void validate(T t) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0) {
            //显示一条出错信息
            ConstraintViolation<T> next = constraintViolations.iterator().next();
            String validateError = next.getMessage();
            throw new ValidationException(validateError);
        }
    }


    public static <T> void validate(T t,Class clazz) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t,clazz.getClass());
        if (constraintViolations.size() > 0) {
            //显示一条出错信息
            String validateError = constraintViolations.iterator().next().getMessage();
            throw new ValidationException(validateError);
        }
    }
}
