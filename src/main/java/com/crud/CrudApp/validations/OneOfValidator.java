package com.crud.CrudApp.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OneOfValidator implements ConstraintValidator<OneOf, Integer> {

	private int[] values;
	
	@Override
	public void initialize(OneOf constraintAnnotation) {
		this.values = constraintAnnotation.values();
	}
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		if (value == null) return true;
		
		boolean found = false;
		for (Integer i : values) {
			if (i.equals(value)) {
				found = true;
				break;
			}
		}		
		return found;
	}
}
