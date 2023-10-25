package org.optionfactory.author;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Transactional
public class TransactionalAuthorFacade implements AuthorFacade {
    private final PasswordEncoder passwordEncoder;
    private final AuthorRepository authorRepository;

    public TransactionalAuthorFacade(PasswordEncoder passwordEncoder, AuthorRepository authorRepository) {
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
    }

    @Override
    public long create(AuthorRequest authorRequest) {
        if (authorRequest.username().isBlank() || authorRequest.password().isBlank()) {
            throw new AuthorIsEmptyException();
        }
        final Optional<Author> author = authorRepository.searchByName(authorRequest.username());
        if (author.isPresent()) {
            throw new AuthorAlreadyExistsException(authorRequest.username());
        }
        return authorRepository.create(Author.withoutId(
                authorRequest.username(),
                passwordEncoder.encode(authorRequest.password()),
                "salt",
                Privileges.USER.name(),
                false,
                Instant.now()
        ));
    }

    @Override
    public Author searchByName(String name) {
        return authorRepository.searchByName(name).orElseThrow(() -> new AuthorNotFoundException(name));
    }

    @Override
    public Author searchById(long id) {
        return authorRepository.searchById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public BootstrapTableAuthorList listByName(String name, int offset, int limit) {
        final int authorCount = authorRepository.count().orElseThrow();
        return new BootstrapTableAuthorList(authorCount, authorCount, authorRepository.listByName(name, offset, limit));
    }

    @Override
    public void updateBlockStatusById(long id, boolean block) {
        authorRepository.updateBlockStatusById(id, block);
    }
}
