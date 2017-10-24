package org.prnhs.javaee.swim.services;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ProgramValidator.class)
public @interface ValidateProgram {
    String message() default "{program.default}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
