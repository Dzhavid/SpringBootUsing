package net.eldarov.usermanager.config;

import net.eldarov.usermanager.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        //  httpServletResponse.sendRedirect("/users");

        User user = (User) authentication.getPrincipal();
        boolean isAdmin = user.getRoles().stream().anyMatch(x -> x.getName().contains("ROLE_ADMIN"));
        String redirect = isAdmin ? "/users" : "/user";
        httpServletResponse.sendRedirect(redirect);


    }
}
