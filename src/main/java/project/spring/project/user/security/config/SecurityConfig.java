package project.spring.project.user.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public final UserDetailsService userDetailsService;



    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(UserDetailsService tabulaUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = tabulaUserDetailsService;

        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authManager) throws Exception {
        authManager.
                userDetailsService(userDetailsService).
                passwordEncoder(passwordEncoder);

    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
              .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/admin-assets/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/").permitAll()
                .and()
                .formLogin().
                loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
//                loginProcessingUrl("/login/authenticate").permitAll().
//                failureForwardUrl("/login-error").
//                successForwardUrl("/home").
//                and().
//                logout().
//                logoutUrl("/logout").
//                logoutSuccessUrl("/login").
//                invalidateHttpSession(true).
//                deleteCookies("JSESSIONID");
    }



}
