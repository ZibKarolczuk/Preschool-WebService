package pl.coderslab.preschool_web_service.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.servlet.ServletRequest;

@Configuration
public class AuthenticationProviderConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    driverManagerDataSource.setUrl("jdbc:mysql://sql.serwer1833957.home.pl:3306/28560350_pws");
	    driverManagerDataSource.setUsername("28560350_pws");
	    driverManagerDataSource.setPassword("!Tpws@DB");
//		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/PreschoolWebService");
//		driverManagerDataSource.setUsername("root");
//		driverManagerDataSource.setPassword("coderslab");
	    return driverManagerDataSource;
	}
    
    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService(){
    	JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
    	jdbcImpl.setDataSource(dataSource());
    	jdbcImpl.setUsersByUsernameQuery("select username, password, enabled from User where username=?");
    	jdbcImpl.setAuthoritiesByUsernameQuery("select b.username, a.roles from User_roles a, User b where b.username=? and a.User_id=b.id");
    	return jdbcImpl;
    }
}