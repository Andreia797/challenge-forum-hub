package com.mafort.forum.domain.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    UserDetails findByEmail(String subject);
}
