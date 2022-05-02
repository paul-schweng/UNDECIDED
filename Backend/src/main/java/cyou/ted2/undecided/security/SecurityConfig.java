package cyou.ted2.undecided.security;

import cyou.ted2.undecided.repository.AuthRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthCookieFilter authCookieFilter;

    private final CustomLogoutSuccessHandler logoutSuccessHandler;

    private final AuthRepository authRepository;

    @Autowired
    private CustomAuthProvider customAuthProvider;

    public SecurityConfig(AuthRepository authRepository) {
        this.authCookieFilter = new AuthCookieFilter(authRepository);
        this.logoutSuccessHandler = new CustomLogoutSuccessHandler();
        this.authRepository = authRepository;
    }

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthProvider);
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .csrf().disable()
                .logout()
                    .logoutUrl("/auth/logout")
                    .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)))
                    .logoutSuccessHandler(this.logoutSuccessHandler)
                    .deleteCookies(AuthCookieFilter.COOKIE_NAME, "JSESSIONID")
                .and()
                .authorizeRequests()
                    .antMatchers("/auth/*").permitAll()
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .addFilterAfter(this.authCookieFilter, SecurityContextPersistenceFilter.class);
    }

    private class CustomLogoutSuccessHandler implements LogoutSuccessHandler {


        @Override
        public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                    Authentication authentication) throws IOException {

            String sessionId = AuthCookieFilter.extractAuthenticationCookie(request);
            System.out.println("\n sessionId: " + sessionId + "\n \n");
            if (sessionId != null && authRepository.existsById(sessionId)) {
                authRepository.deleteById(sessionId);
            }

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().flush();
        }

    }

}
