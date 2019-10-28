package com.tzxylao.java8.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author laoliangliang
 * @date 2019/10/22 15:53
 */
@Documented
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCardValid {

    String message() default "身份证不合法";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
