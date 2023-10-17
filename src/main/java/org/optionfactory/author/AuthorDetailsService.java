package org.optionfactory.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class AuthorDetailsService implements UserDetailsService {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        final Optional<Author> optionalAuthor = authorRepository.searchByName(name);
        if(optionalAuthor.isEmpty()) {
            throw new UsernameNotFoundException(name);
        }
        return new AuthorDetails(optionalAuthor.get());
    }
}
