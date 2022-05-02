package cyou.ted2.undecided.security;

import cyou.ted2.undecided.models.Authentication;
import cyou.ted2.undecided.models.User;
import cyou.ted2.undecided.repository.AuthRepository;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;


public class AuthCookieFilter extends GenericFilterBean {

    public final static String COOKIE_NAME = "auth";

    private AuthRepository authRepository;

    public AuthCookieFilter(AuthRepository authRepository){
        this.authRepository = authRepository;
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {


        if(SecurityContextHolder.getContext().getAuthentication() != null){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String sessionId = extractAuthenticationCookie(httpServletRequest);

        if (sessionId != null) {
            User user = getUserFromCookie(sessionId);
            if (user != null) {
                SecurityContextHolder.getContext().setAuthentication( new UsernamePasswordAuthenticationToken(user.getId(), user.getPassword(), Collections.emptyList()) );
                System.out.println("USER LOGGED IN WITH COOKIE");
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private User getUserFromCookie(String cookie){
        Authentication auth =  authRepository.findById(cookie).orElse(null);
        return auth != null ? auth.getUser() : null;
    }

    public static String extractAuthenticationCookie(HttpServletRequest httpServletRequest) {
        String sessionId = null;
        Cookie[] cookies = httpServletRequest.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AuthCookieFilter.COOKIE_NAME)) {
                    sessionId = cookie.getValue();
                    break;
                }
            }
        }
        return sessionId;
    }
}