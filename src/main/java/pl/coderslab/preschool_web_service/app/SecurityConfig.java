package pl.coderslab.preschool_web_service.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/register", "/login").permitAll()

//                .antMatchers("/").access("hasRole('ROLE_ADMIN')")
//                .anyRequest().permitAll()
//                .antMatchers("/").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")

                .antMatchers("/", "/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//                .antMatchers("/user/update").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
//                .antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")

//				  .antMatchers().access("hasAnyRole('ROLE_ADMIN')")
// 				  .anyRequest().access("hasRole('ROLE_ADMIN')")

                .and().formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)

//				  .successForwardUrl("/test")
//				  .defaultSuccessUrl("/test")
//				  .and().csrf()

                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

//				  .logoutSuccessUrl("/").deleteCookies("JSESSIONID").invalidateHttpSession(true)
//		          .and()
//				  .formLogin()
//				  .loginPage("/login.html")
//				  .defaultSuccessUrl("/homepage.html")
//				  .failureUrl("/login.html?error=true")
//				  .and()
//				  .logout().logoutSuccessUrl("/login.html");

    }

    // for db users

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
}