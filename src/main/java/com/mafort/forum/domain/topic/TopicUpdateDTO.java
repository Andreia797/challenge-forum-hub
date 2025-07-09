package com.mafort.forum.domain.topic;

import com.mafort.forum.domain.author.AuthorDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record TopicUpdateDTO(
        @NotNull
        Long id,
        String title,
        String message,
        AuthorDTO authorDTO,
        String nameCourse
) {
}
