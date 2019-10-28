package com.tzxylao.java8.valid;

import org.springframework.validation.annotation.Validated;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author laoliangliang
 * @date 2019/10/22 15:19
 */
public class ValidLearn {
    public static void main(String[] args) {
        ValidLearn learn = new ValidLearn();
        learn.testValid(new Order().setIdcard("33062119981012361X").setName("  ").setCreateDate(new Date()));
    }

    public void testValid(@Validated Order order) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        //分组Insert.class则id为空不检验
        Set<ConstraintViolation<Order>> validate = validator.validate(order, Insert.class);
        Stream.of(validate).forEach(action -> {
            for (ConstraintViolation<Order> orderConstraintViolation : action) {
                String message = orderConstraintViolation.getMessage();
                System.out.println(message);
            }
        });
    }
}
