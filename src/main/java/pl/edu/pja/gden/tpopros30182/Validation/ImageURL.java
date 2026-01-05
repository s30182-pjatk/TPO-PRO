package pl.edu.pja.gden.tpopros30182.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ImageURLValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ImageURL {
    String message() default "Provided URL in not of image type";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
