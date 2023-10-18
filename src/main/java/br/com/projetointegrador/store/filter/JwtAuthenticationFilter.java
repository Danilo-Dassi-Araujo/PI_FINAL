package br.com.projetointegrador.store.filter;

import br.com.projetointegrador.store.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    private static final String BEARER_NAME_VALUE = "Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authToken = this.getAuthTokenFromRequest(request);

        if (StringUtils.hasText(authToken)) {
            var userEmail = this.jwtService.validateToken(authToken);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                var userDetails = this.userDetailsService.loadUserByUsername(userEmail);

                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }

    private boolean isValidAuthHeader(String header) {
        return !ObjectUtils.isEmpty(header) && header.startsWith(BEARER_NAME_VALUE);
    }

    private String getAuthTokenFromRequest(HttpServletRequest httpServletRequest) {
        var authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (this.isValidAuthHeader(authHeader)) {
            return authHeader.substring(BEARER_NAME_VALUE.length());
        }

        return null;
    }

}
