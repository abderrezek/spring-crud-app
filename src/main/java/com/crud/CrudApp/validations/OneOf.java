package com.crud.CrudApp.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OneOfValidator.class)
@Documented
public @interface OneOf {
	
	String message() default "{com.crud.CrudApp.validations.OneOf.message}";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	int[] values() default { };

}