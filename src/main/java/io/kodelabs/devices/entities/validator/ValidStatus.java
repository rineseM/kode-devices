package io.kodelabs.devices.entities.validator;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidStatus.StatusValidator.class)
public @interface ValidStatus {
  String message() default "Invalid status";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  Class<? extends Enum<?>> enumClass();

  class StatusValidator implements ConstraintValidator<ValidStatus, String> {
    private Class<? extends Enum<?>> enumClass;

    @Override
    public void initialize(ValidStatus constraintAnnotation) {
      enumClass = constraintAnnotation.enumClass();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
      if (value == null) {
        return true;
      }
      for (Enum<?> enumValue : enumClass.getEnumConstants()) {
        if (enumValue.name().equals(value)) {
          return true;
        }
      }

      return false;
    }
  }
}
