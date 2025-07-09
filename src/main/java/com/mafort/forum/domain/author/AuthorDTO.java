package com.mafort.forum.domain.author;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record AuthorDTO(
        @NotEmpty
        String name,
        @NotEmpty @Email
        String email,
        @NotEmpty
        String password
) {

}
