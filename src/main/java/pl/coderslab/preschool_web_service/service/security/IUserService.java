package pl.coderslab.preschool_web_service.service.security;

import pl.coderslab.preschool_web_service.model.security.UserDto;
import pl.coderslab.preschool_web_service.validation.security.EmailExistsException;
import pl.coderslab.preschool_web_service.validation.security.UsernameExistsException;


public interface IUserService {
	pl.coderslab.preschool_web_service.entity.security.User registerNewUserAccount(UserDto accountDto)
			throws EmailExistsException, UsernameExistsException;
}