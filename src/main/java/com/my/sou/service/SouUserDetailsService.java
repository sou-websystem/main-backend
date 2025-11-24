package com.my.sou.service;

import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.my.sou.entity.User;

@Service
public class SouUserDetailsService implements UserDetailsService {

  private Logger LOG = LoggerFactory.getLogger(SouUserDetailsService.class);

  private final UserRepository userRepository;

  public SouUserDetailsService(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<User> opt = userRepository.findByEmail(email);
    LOG.debug("user exist? {}", Boolean.toString(opt.isPresent()));
    if (opt.isEmpty())
      throw new UsernameNotFoundException("User not found");

    User user = opt.get();
    var authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getRoldId())).collect(Collectors.toList());

    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getPassword(), authorities);
  }
}
