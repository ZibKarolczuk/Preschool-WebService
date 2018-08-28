package pl.coderslab.preschool_web_service.validation.security;

import pl.coderslab.preschool_web_service.model.security.UserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> { 

  public void initialize(PasswordMatches constraintAnnotation) {       
  }

  public boolean isValid(Object obj, ConstraintValidatorContext context){   
      UserDto user = (UserDto) obj;
      return user.getPassword().equals(user.getMatchingPassword());    
  }
}