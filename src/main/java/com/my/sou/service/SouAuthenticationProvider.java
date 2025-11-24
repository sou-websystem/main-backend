package com.my.sou.service;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.my.sou.entity.User;

@Component
public class SouAuthenticationProvider extends DaoAuthenticationProvider {

  private final PasswordEncoder passwordEncoder;

  private final UserRepository userRepository;

  @Autowired
  public SouAuthenticationProvider(SouUserDetailsService userDetailsService,
      PasswordEncoder passwordEncoder, final UserRepository userRepository) {
    super.setUserDetailsService(userDetailsService);
    super.setPasswordEncoder(passwordEncoder);
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    if (authentication.getCredentials() == null) {
      this.logger.debug("Failed to authenticate since no credentials provided");
      throw new BadCredentialsException(this.messages.getMessage(
          "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }
    String presentedPassword = authentication.getCredentials().toString();
    if (!verifyPassword(authentication.getPrincipal().toString(), presentedPassword)) {
      this.logger.debug("Failed to authenticate since password does not match stored value");
      throw new BadCredentialsException(this.messages.getMessage(
          "AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
    }
  }

  private boolean verifyPassword(final String email, final String encodedPassword) {
    User user = userRepository.findByEmail(email)
        .orElseThrow(() -> new SecurityException("email/password incorrect"));
    byte[] decodedBytes = Base64.getDecoder().decode(encodedPassword);
    String rawPassword = new String(decodedBytes);
    if (!this.passwordEncoder.matches(rawPassword, user.getPassword())) {
      return false;
    }
    return true;
  }
}
