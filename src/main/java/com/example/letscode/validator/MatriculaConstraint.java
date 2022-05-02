package com.example.letscode.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatriculaValidator.class)
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
public @interface MatriculaConstraint {
    String message() default "Matrícula inválida - Formato MTRAXXXXXX";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}