package pl.coderslab.preschool_web_service.service.security;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.preschool_web_service.entity.UserDetails;
import pl.coderslab.preschool_web_service.entity.security.User;
import pl.coderslab.preschool_web_service.model.security.UserDto;
import pl.coderslab.preschool_web_service.repository.UserDetailsRepository;
import pl.coderslab.preschool_web_service.repository.security.UserRepository;
import pl.coderslab.preschool_web_service.validation.security.EmailExistsException;

@Service
public class UserService implements IUserService {
	@Autowired
	private UserRepository repository;

	@Autowired
    private UserDetailsRepository udr;

	@Transactional
    public User registerNewUserAccount(UserDto accountDto)
      throws EmailExistsException {
         
        if (emailExist(accountDto.getEmail())) {   
            throw new EmailExistsException(
              "There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();    
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_ADMIN"));
        UserDetails userDetails = new UserDetails();
//        userDetails.setId(user.getId());
        userDetails.setEmail(accountDto.getEmail());
        this.udr.save(userDetails);
        return repository.save(user);       
    }

	private boolean emailExist(String email) {
		User user = repository.findByEmail(email);
		if (user != null) {
			return true;
		}
		return false;
	}
}