package it.springcataloglogin.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, String> {

	private String firstFieldName;
    private String secondFieldName;
    private String message;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstFieldName = constraintAnnotation.first();
		secondFieldName = constraintAnnotation.second();
		message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		boolean valid = true;
		try {
			final Object firstObj = new BeanWrapperImpl(value).getPropertyValue(firstFieldName);
            final Object secondObj = new BeanWrapperImpl(value).getPropertyValue(secondFieldName);

            valid = firstObj.equals(secondObj);
		} catch (final Exception ignore) {	}
		
        if (!valid) {
            context.buildConstraintViolationWithTemplate(message)
            		.addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
		
		return valid;
	}

}
