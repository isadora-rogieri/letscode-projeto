package com.example.letscode.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatriculaValidator implements ConstraintValidator<MatriculaConstraint, String> {

    @Override
    public void initialize(MatriculaConstraint matricula){

    }
    @Override
    public boolean isValid(String matricula, ConstraintValidatorContext constraintValidatorContext){
        return matricula != null && matricula.matches("^MTLA\\d{6}$");
    }
}