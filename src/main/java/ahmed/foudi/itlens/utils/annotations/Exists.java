package ahmed.foudi.itlens.utils.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ExistsValidator.class)
@Target({ElementType.PARAMETER , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {
    Class<?> entityClass();

    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
