package com.my.sou.config;

import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.my.sou.service.SouUserDetailsService;
import com.my.sou.service.TokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final SouUserDetailsService userDetailsService;
  private final TokenRepository tokenRepository;

  public JwtFilter(final JwtUtil jwtUtil, final SouUserDetailsService userDetailsService,
      final TokenRepository tokenRepository) {
    this.jwtUtil = jwtUtil;
    this.userDetailsService = userDetailsService;
    this.tokenRepository = tokenRepository;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain) throws ServletException, IOException {
    if (request.getServletPath().startsWith("/public/")) {
      chain.doFilter(request, response);
      return;
    }

    try {
      String authHeader = request.getHeader("Authorization");
      String token = null;
      String email = null;

      if (authHeader != null && authHeader.startsWith("Bearer ")) {
        token = authHeader.substring(7);
        email = jwtUtil.getEmail(token);
      }

      if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        var tokenEntity = tokenRepository.findByToken(token);

        if (tokenEntity.isPresent() && tokenEntity.get().isRevoked()) {
          response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          return;
        }

        if (jwtUtil.validateToken(token)) {
          UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
              userDetails, null, userDetails.getAuthorities());
          authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
          SecurityContextHolder.getContext().setAuthentication(authToken);
        }
      }

      chain.doFilter(request, response);

    } catch (Exception e) {
      throw e;
    }
  }


  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    return request.getServletPath().equals("/public/auth/login");
  }
}
