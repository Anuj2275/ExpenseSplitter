package com.learning.ExpenseSplitter.config;

import com.learning.ExpenseSplitter.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

/*JWT filter job
1. Read Authorization header
2. Extract token
3. Validate token
4. Set user in context
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        //If no token → skip filter → go next
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
//        extract email
        String email = jwtService.extractEmail(token);
//        validate token
        if (email != null && jwtService.isTokenValid(token)) {
//            System.out.println("Valid token for user: "+email);

            //email = identity
            //null = no password needed
            //roles = empty for now
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            email, null, new ArrayList<>()
                    );
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }


}
