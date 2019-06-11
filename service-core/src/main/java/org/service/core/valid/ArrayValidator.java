package org.service.core.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ArrayValidator implements ConstraintValidator<ArrayConstraint, Object> {
	Class<?> cls;

	@Override
	public void initialize(ArrayConstraint constraint) {
		this.cls = constraint.target();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		return false;
	}
}