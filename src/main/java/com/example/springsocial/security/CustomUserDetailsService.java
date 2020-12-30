package com.example.springsocial.security;


import com.example.springsocial.model.User;
import com.example.springsocial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Optional;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    final
    UserRepository userRepository;

    final
    ResourceBundleMessageSource messageSource;

    final
    LocaleResolver localeResolver;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ResourceBundleMessageSource messageSource, LocaleResolver localeResolver) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
        this.localeResolver = localeResolver;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.map(UserPrincipal::create).orElse(null);
    }

    @Transactional
    public Optional<UserDetails> loadUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserPrincipal::create);
    }
}